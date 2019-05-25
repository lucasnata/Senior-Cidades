package br.com.lucas.cidades.service;

import br.com.lucas.cidades.model.*;
import br.com.lucas.cidades.model.DTO.CityDTO;
import br.com.lucas.cidades.model.DTO.CityName;
import br.com.lucas.cidades.model.DTO.NumberCitiesState;
import br.com.lucas.cidades.model.entity.City;
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

    public Iterable<CityDTO> getCitiesCapitals() {
        return CityDTO.convertListCidadeToListCidadeDTO((List<City>) cityRepository.findByCapitalTrueOrderByName());
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

    public Iterable<CityName> getCitiesNameByState(Estado uf) {
        return cityRepository.findNameByUf(uf.toString());
    }
}
