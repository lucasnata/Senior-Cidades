package br.com.lucas.cidades.controller;

import br.com.lucas.cidades.service.CityCustomService;
import br.com.lucas.cidades.model.DTO.CityDTO;
import br.com.lucas.cidades.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class CitiesController extends ControllerExceptions {

    @Autowired
    CityService cityService;

    @Autowired
    CityCustomService cityCustomService;

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
        return cityService.getCitybyIbgeId(ibgeId);
    }

    @RequestMapping(value = "/cidades", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCidade(@RequestBody CityDTO city) {
        cityService.saveCity(city);
    }

    @RequestMapping(value = "/cidades", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void removeCidade(@PathVariable Integer ibgeId) {
        cityService.removeCity(ibgeId);
    }

    @RequestMapping(value = "/cidades/total", produces = {"application/json"})
    @ResponseBody
    public String totalCidades() {
        return "{\"total_registros\": " + cityService.getTotalCities() + "}";
    }

    @RequestMapping(value = "cidades/busca", produces = {"application/json"})
    @ResponseBody
    public List<CityDTO> findCidadeColuna(@RequestParam("coluna") String columName, @RequestParam("busca") String busca) {
        return cityService.findInColumnByString(columName, busca);
    }

    @RequestMapping(value = "cidades/total_coluna/{columName}", produces = {"application/json"})
    @ResponseBody
    public String getTotalCidadesColuna(@PathVariable String columName) {
        return "{\"total\": " + cityCustomService.countColumnName(columName) + "}";
    }
}
