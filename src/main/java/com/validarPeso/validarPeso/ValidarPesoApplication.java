package com.validarPeso.validarPeso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.validarPeso.validarPeso"})
@EnableAutoConfiguration
public class ValidarPesoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidarPesoApplication.class, args);
	}

}
