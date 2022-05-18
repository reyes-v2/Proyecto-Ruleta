package dev.rmpedro.appruleta.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInfo {
    @Bean
    public OpenAPI springAppOpenAPI() {
        OpenAPI openAPI = new OpenAPI();

        openAPI.info(new Info().title("API Ruleta Documentacion").version("v.1.0.0")
                .description("A traves de esta API podras interactuar con el funcionamiento de " +
                        "una ruleta de casino tradicional")
                .contact(new Contact().name("Pedro Reyes").email(
                "rm.pedro@ibm.com"
        )));
        return openAPI;

    }
}
