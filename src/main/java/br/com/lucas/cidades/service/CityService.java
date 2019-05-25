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
        List<CityDTO> cities = CsvUtil.loadObjectList(CityDTO.class, fis);
        cities.forEach(city -> saveCity(city));
    }

    public List<CityDTO> getAllCities() {
        return CityDTO.convertListCidadeToListCidadeDTO((List<City>) cityRepository.findAll());
    }

    public CityDTO saveCity(CityDTO cityDTO) {
        return CityDTO.convertCidadeToCidadeDTO(cityRepository.save(CityDTO.convertCidadeDTOToCidade(cityDTO)));
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
}
