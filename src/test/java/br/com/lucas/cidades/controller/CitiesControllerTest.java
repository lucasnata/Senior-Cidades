package br.com.lucas.cidades.controller;

import br.com.lucas.cidades.service.CityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CitiesController.class)
public class CitiesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CityService cityService;

    @Test
    public void whenGetCities() throws Exception {
        mockMvc.perform(get("/cidades"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetCitiesById() throws Exception {
        mockMvc.perform(get("/cidades/1100015")).andExpect(status().isOk());
    }
}
