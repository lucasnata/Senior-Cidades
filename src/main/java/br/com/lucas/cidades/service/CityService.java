package br.com.lucas.cidades.service;

import br.com.lucas.cidades.model.City;
import br.com.lucas.cidades.model.CityDTO;
import br.com.lucas.cidades.model.CityRepository;
import br.com.lucas.cidades.util.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public void importCsv(InputStream fis) throws Exception {
        List<City> cities = CsvUtil.loadObjectList(City.class, fis);
        cities.forEach(city -> saveCity(city));
    }

    public List<CityDTO> getAllCities() {
        return CityDTO.convertListCidadeToListCidadeDTO((List<City>) cityRepository.findAll());
    }

    public void saveCity(City city) {
        cityRepository.save(city);
    }

    public Iterable<City> getCitybyIbgeId(Integer ibgeId) {
        return cityRepository.findByIbgeId(ibgeId);
    }
}
