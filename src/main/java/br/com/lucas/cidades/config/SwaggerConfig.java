package br.com.lucas.cidades.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("1.0.0")
    private String version;

    @Value("Cidades")
    private String name;

    @Value("Senior Cidades")
    private String description;

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.lucas.cidades"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(name, description, version,
                "Terms of Service",
                new Contact("Lucas Nascimento", "https://github.com/lucasnata","lucas.natanael.f@gmail.com"),
                "MIT",
                "https://opensource.org/licenses/MIT",
                Collections.emptyList());
    }
}
