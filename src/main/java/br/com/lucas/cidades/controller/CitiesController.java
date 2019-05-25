package br.com.lucas.cidades.controller;

import br.com.lucas.cidades.model.CityDTO;
import br.com.lucas.cidades.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class CitiesController {

    private final CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/cidades/importcsv")
    @ResponseStatus(HttpStatus.OK)
    public void importCsv(@RequestParam("file") MultipartFile file) throws Exception {
        // TODO - Validar tipo de arquivo
        cityService.importCsv(file.getInputStream());
    }

    @RequestMapping(value = "/cidades", produces = {"application/json"}, method = RequestMethod.GET)
    @ResponseBody
    public Iterable<CityDTO> getCidades() {
        return cityService.getAllCities();
    }

    @RequestMapping(value = "/cidades/{ibgeId}", produces = {"application/json"}, method = RequestMethod.GET)
    @ResponseBody
    public CityDTO getCidade(@PathVariable Integer ibgeId) {
        // TODO - Exibir mensagem para ibgeId inválido
        return cityService.getCitybyIbgeId(ibgeId);
    }

    @RequestMapping(value = "/cidades", produces = {"application/json"}, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCidade(@RequestBody CityDTO city) {
        cityService.saveCity(city);
    }
}
