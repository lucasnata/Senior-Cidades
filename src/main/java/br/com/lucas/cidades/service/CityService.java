package br.com.lucas.cidades.service;

import br.com.lucas.cidades.model.*;
import br.com.lucas.cidades.model.DTO.CityDTO;
import br.com.lucas.cidades.model.DTO.MaisDistantes;
import br.com.lucas.cidades.model.entity.City;
import br.com.lucas.cidades.model.entity.DistancesId;
import br.com.lucas.cidades.model.entity.Distancies;
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

    @Autowired
    DistanceRepository distanceRepository;

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
        /* Inviável resolver assim, melhor seria um processo separado calculando
        e o service apenas consultar as distancias */

        setLatLonDistancias();
        setDistancias();

        List<City> cities = getCidadesMaisDistantes();

        return CityDTO.convertListCidadeToListCidadeDTO(cities);
    }

    private List<City> getCidadesMaisDistantes() {

        List<City> cities = new ArrayList<>();
        MaisDistantes mostDistant = distanceRepository.findMostDistant();
        cities.add(cityRepository.findByIbgeId(mostDistant.getIgbeId1()));
        cities.add(cityRepository.findByIbgeId(mostDistant.getIgbeId2()));

        return cities;
    }

    private void setDistancias() {
        Iterable<Distancies> distancies = distanceRepository.findAll();

        distancies.forEach(distancie -> {
            distancie.setDistancia(
                    calculaDistancia(
                            distancie.getLatitude1(),
                            distancie.getLongitude1(),
                            distancie.getLatitude2(),
                            distancie.getLongitude2()
                    )
            );

            distanceRepository.save(distancie);
        });
    }

    private void setLatLonDistancias() {
        Iterable<City> allCities = cityRepository.findAll();

        for (City city : allCities) {

            Distancies distancies = new Distancies();
            distancies.setLatitude1(city.getLatitude());
            distancies.setLongitude1(city.getLongitude());

            for (City city2 : allCities) {

                distancies.setLatitude2(city2.getLatitude());
                distancies.setLongitude2(city2.getLongitude());

                DistancesId distancesId = new DistancesId(city.getIbgeId(), city2.getIbgeId());
                distancies.setIbgeId(distancesId);

                distanceRepository.save(distancies);
            }
        }
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
