package br.com.lucas.cidades.controller;

import br.com.lucas.cidades.model.*;
import br.com.lucas.cidades.model.DTO.CityDTO;
import br.com.lucas.cidades.model.DTO.CityName;
import br.com.lucas.cidades.model.DTO.NumberCitiesState;
import br.com.lucas.cidades.service.StateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatesController extends ControllerExceptions{

    @Autowired
    StateService stateService;

    @GetMapping("/estado/capitais")
    public Iterable<CityDTO> getCapitais() {
        return stateService.getCitiesCapitals();
    }

    @GetMapping("/estado/maior_menor")
    public @ResponseBody
    String getEstados() throws JsonProcessingException {
        return stateService.getNumberCitiesStateMinMax();
    }

    @GetMapping("/estado/numero_cidades")
    public Iterable<NumberCitiesState> getCidadesEstado() {
        return stateService.getCountCitiesByUf();
    }

    @GetMapping("/estado/{uf}")
    public Iterable<CityName> getCidadesEstado(@PathVariable Estado uf) {
        return stateService.getCitiesNameByState(uf);
    }
}
