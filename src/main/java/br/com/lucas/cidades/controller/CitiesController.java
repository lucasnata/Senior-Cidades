package br.com.lucas.cidades.controller;

import br.com.lucas.cidades.service.CityCustomService;
import br.com.lucas.cidades.model.DTO.CityDTO;
import br.com.lucas.cidades.service.CityService;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(produces = {"application/json"})
public class CitiesController {

    @Autowired
    CityService cityService;

    @Autowired
    CityCustomService cityCustomService;

    @PostMapping("/cidades/importcsv")
    @ResponseStatus(HttpStatus.OK)
    public void importCsv(@RequestParam("file") MultipartFile file) throws Exception {
        if(!file.getContentType().contains("text/csv")) throw new FileUploadBase.InvalidContentTypeException();
        cityService.importCsv(file.getInputStream());
    }

    @GetMapping("/cidades")
    @ResponseBody
    public Iterable<CityDTO> getCidades() {
        return cityService.getAllCities();
    }

    @GetMapping("/cidades/{ibgeId}")
    @ResponseBody
    public CityDTO getCidade(@PathVariable Integer ibgeId) {
        return cityService.getCitybyIbgeId(ibgeId);
    }

    @PostMapping("/cidades")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCidade(@RequestBody CityDTO city) {
        cityService.saveCity(city);
    }

    @DeleteMapping("/cidades")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void removeCidade(@PathVariable Integer ibgeId) {
        cityService.removeCity(ibgeId);
    }

    @GetMapping("/cidades/total")
    @ResponseBody
    public String totalCidades() {
        return "{\"total_registros\": " + cityService.getTotalCities() + "}";
    }

    @GetMapping("cidades/busca")
    @ResponseBody
    public List<CityDTO> findCidadeColuna(@RequestParam("coluna") String columName, @RequestParam("busca") String busca) {
        return cityService.findInColumnByString(columName, busca);
    }

    @GetMapping("cidades/total_coluna/{columName}")
    @ResponseBody
    public String getTotalCidadesColuna(@PathVariable String columName) {
        return "{\"total\": " + cityCustomService.countColumnName(columName) + "}";
    }

    @GetMapping("/cidadas/distancias")
    public List<CityDTO> getDistancias() {
        return cityService.getDistancias();
    }
}
