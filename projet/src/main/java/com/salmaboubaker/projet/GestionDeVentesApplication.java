package com.salmaboubaker.projet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.salmaboubaker.projet")

public class GestionDeVentesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionDeVentesApplication.class, args);
	}






}
