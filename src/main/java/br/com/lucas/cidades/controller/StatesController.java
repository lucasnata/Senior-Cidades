package br.com.lucas.cidades.controller;

import br.com.lucas.cidades.model.City;
import br.com.lucas.cidades.model.CityName;
import br.com.lucas.cidades.model.NumberCitiesState;
import br.com.lucas.cidades.service.StateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatesController {

    @Autowired
    StateService stateService;

    @GetMapping("/estado/capitais")
    public Iterable<City> getCapitais() {
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
    public Iterable<CityName> getCidadesEstado(@PathVariable String uf) {
        return stateService.getCitiesNameByState(uf);
    }
}
