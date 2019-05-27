package br.com.lucas.cidades.controller;

import br.com.lucas.cidades.model.DTO.CityDTO;
import br.com.lucas.cidades.model.FakeCity;
import br.com.lucas.cidades.service.CityCustomService;
import br.com.lucas.cidades.service.CityService;
import br.com.lucas.cidades.service.StateService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class StatesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StateService stateService;

    @MockBean
    CityService cityService;

    @MockBean
    CityCustomService cityCustomService;

    @Test
    public void whenGetCapitalsByState_thenReturnJsonCity() throws Exception {

        CityDTO city = CityDTO.convertCidadeToCidadeDTO(FakeCity.getFakeCapitalCity());

        cityService.saveCity(city);

        given(stateService.getCitiesCapitals()).willReturn(Arrays.asList(city));

        mockMvc.perform(get("/estado/capitais")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(containsString("\"capital\":true")));
    }

    @Test
    public void whenGetBetterSmallerNumberOfCitiesByState_thenReturnJson() throws Exception {
        mockMvc.perform(get("/estado/maior_menor")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetNumberOfCitiesByState_thenReturnJson() throws Exception {
        mockMvc.perform(get("/estado/numero_cidades")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetCitiesByState_thenReturnJson() throws Exception {
        mockMvc.perform(get("/estado/MA")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void whenGetCitiesByStateWithInvalidUf_thenReturn400() throws Exception {
        mockMvc.perform(get("/estado/2"))
                .andExpect(status().is4xxClientError());
    }

}
