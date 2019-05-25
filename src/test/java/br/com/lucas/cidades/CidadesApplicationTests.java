package br.com.lucas.cidades;

import br.com.lucas.cidades.controller.CitiesController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CidadesApplicationTests {

    @Autowired
    private CitiesController citiesController;

    @Test
    public void contextLoads() {
        assertThat(citiesController).isNotNull();
    }

}
