package com.ibm.academia.universidadesapirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class UniversidadApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversidadApiApplication.class, args);
    }

}
