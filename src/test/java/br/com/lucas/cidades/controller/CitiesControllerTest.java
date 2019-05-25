package br.com.lucas.cidades.controller;

import br.com.lucas.cidades.model.entity.City;
import br.com.lucas.cidades.model.DTO.CityDTO;
import br.com.lucas.cidades.model.FakeCity;
import br.com.lucas.cidades.service.CityCustomService;
import br.com.lucas.cidades.service.CityService;
import br.com.lucas.cidades.service.StateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CitiesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CityService cityService;

    @MockBean
    CityCustomService cityCustomService;

    @MockBean
    StateService stateService;

    @Test
    public void whenGetAllCities_thenReturnJsonCities() throws Exception {

        CityDTO city = CityDTO.convertCidadeToCidadeDTO(FakeCity.getFakeCity());

        cityService.saveCity(city);

        given(cityService.getAllCities()).willReturn(Arrays.asList(city));

        mockMvc.perform(get("/cidades")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Presidente Mdici\"")));
    }

    @Test
    public void whenGetCityById_thenReturnCity() throws Exception {

        CityDTO city = CityDTO.convertCidadeToCidadeDTO(FakeCity.getFakeCity());

        cityService.saveCity(city);

        given(cityService.getCitybyIbgeId(city.getIbgeId())).willReturn(city);

        mockMvc.perform(get("/cidades/" + city.getIbgeId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Presidente Mdici\"")));
    }

    @Test
    public void whenGetCityWhithInvalidIbgeId_thenReturn400() throws Exception {
        mockMvc.perform(get("/cidades/A"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenPostCity() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        City fakeCity = FakeCity.getFakeCity();
        fakeCity.setIbgeId(99999999);
        String cityJson = objectMapper.writeValueAsString(fakeCity);

        mockMvc.perform(post("/cidades")
                .contentType("application/json")
                .characterEncoding("UTF-8")
                .content(cityJson))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void whenGetTotalCities() throws Exception {
        mockMvc.perform(get("/cidades/total"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenFindCityByCustomColumn() throws Exception {
        mockMvc.perform(get("/cidades/busca?coluna=ibgeId&busca=1600303"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenFindCityByInvalidCustomColumn() throws Exception {
        mockMvc.perform(get("/cidades/busca?coluna=ibge_id&busca=A"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenCountCustomColumn() throws Exception {
        mockMvc.perform(get("/cidades/total_coluna/name"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
