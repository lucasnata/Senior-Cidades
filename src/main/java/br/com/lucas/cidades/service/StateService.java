package br.com.lucas.cidades.service;

import br.com.lucas.cidades.model.City;
import br.com.lucas.cidades.model.CityName;
import br.com.lucas.cidades.model.CityRepository;
import br.com.lucas.cidades.model.NumberCitiesState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StateService {

    @Autowired
    CityRepository cityRepository;

    public Iterable<City> getCitiesCapitals() {
        return cityRepository.findByCapitalTrue();
    }

    public String getNumberCitiesStateMinMax() throws JsonProcessingException {

        List<NumberCitiesState> numberCitiesStates = new ArrayList<>();
        numberCitiesStates.add(cityRepository.findUfMinCidades());
        numberCitiesStates.add(cityRepository.findUfMaxCidades());

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(numberCitiesStates);
    }

    public Iterable<NumberCitiesState> getCountCitiesByUf() {
        return cityRepository.countByUf();
    }

    public Iterable<CityName> getCitiesNameByState(String uf) {
        return cityRepository.findNameByUf(uf);
    }
}
