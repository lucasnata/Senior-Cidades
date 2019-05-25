package br.com.lucas.cidades.controller;

import br.com.lucas.cidades.service.StateService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StatesController.class)
public class StatesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StateService stateService;

    @Test
    public void whenGetCapitalsByState() throws Exception {
        mockMvc.perform(get("/estado/capitais"))
                .andExpect(status().isOk());
    }

}
