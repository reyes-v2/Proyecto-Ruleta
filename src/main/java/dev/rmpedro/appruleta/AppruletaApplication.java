package dev.rmpedro.appruleta;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition
@SpringBootApplication
public class AppruletaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppruletaApplication.class, args);
    }

}
