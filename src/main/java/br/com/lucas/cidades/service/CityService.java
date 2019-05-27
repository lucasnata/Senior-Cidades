package br.com.lucas.cidades.service;

import br.com.lucas.cidades.model.*;
import br.com.lucas.cidades.model.dto.CityDTO;
import br.com.lucas.cidades.model.entity.CidadeGPS;
import br.com.lucas.cidades.model.entity.City;
import br.com.lucas.cidades.model.entity.DistanciasCalculadas;
import br.com.lucas.cidades.util.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public void importCsv(InputStream fis) throws Exception {
        List<CityDTO> cities = CsvUtil.loadObjectList(CityDTO.class, fis);
        cities.forEach(city -> saveCity(city));
    }

    public List<CityDTO> getAllCities() {
        return CityDTO.convertListCidadeToListCidadeDTO((List<City>) cityRepository.findAll());
    }

    public CityDTO saveCity(CityDTO cityDTO) {
        City city = CityDTO.convertCidadeDTOToCidade(cityDTO);
        return CityDTO.convertCidadeToCidadeDTO(cityRepository.save(city));
    }

    public CityDTO getCitybyIbgeId(Integer ibgeId) {
        return CityDTO.convertCidadeToCidadeDTO(cityRepository.findByIbgeId(ibgeId));
    }

    public void removeCity(Integer ibgeId) {
        cityRepository.delete(cityRepository.findByIbgeId(ibgeId));
    }

    public long getTotalCities() {
        return cityRepository.count();
    }

    public List<CityDTO> findInColumnByString(String columName, String columnValue) {
        CitySpecification citySpecification = new CitySpecification(columName, columnValue);
        return CityDTO.convertListCidadeToListCidadeDTO(cityRepository.findAll(citySpecification));
    }

    public List<CityDTO> getDistancias() {

        DistanciasCalculadas calculedDistanciesCities = getCalculedDistanciesCities();

        List<City> listCities = new ArrayList<>();
        listCities.add(cityRepository.findByIbgeId(calculedDistanciesCities.getCidadeGPS1().getIbgeId()));
        listCities.add(cityRepository.findByIbgeId(calculedDistanciesCities.getCidadeGPS2().getIbgeId()));

        return CityDTO.convertListCidadeToListCidadeDTO(listCities);
    }

    private DistanciasCalculadas getCalculedDistanciesCities() {

        Iterable<City> allCities = cityRepository.findAll();

        DistanciasCalculadas mostDistant = new DistanciasCalculadas();

        for (City city : allCities) {

            DistanciasCalculadas calculedCities = new DistanciasCalculadas();
            calculedCities.setCidadeGPS1(
                    new CidadeGPS(
                            city.getIbgeId(),
                            city.getLatitude(),
                            city.getLongitude()));

            for (City city2 : allCities) {

                calculedCities.setCidadeGPS2(
                        new CidadeGPS(
                                city2.getIbgeId(),
                                city2.getLatitude(),
                                city2.getLongitude()));

                calculedCities.setDistancia(calculaDistancia(
                        calculedCities.getCidadeGPS1().getLatitude(), calculedCities.getCidadeGPS1().getLongitude(),
                        calculedCities.getCidadeGPS2().getLatitude(), calculedCities.getCidadeGPS2().getLongitude()
                ));

                if (calculedCities.getDistancia() > mostDistant.getDistancia()) {
                    mostDistant = new DistanciasCalculadas(
                            calculedCities.getCidadeGPS1(),
                            calculedCities.getCidadeGPS2(),
                            calculedCities.getDistancia());
                }

            }
        }
        return mostDistant;
    }

    private double calculaDistancia(double lat1, double lng1, double lat2, double lng2) {

        double earthRadius = 6371;//kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        return dist;
    }
}
