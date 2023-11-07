package com.prueba.comercio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.prueba.comercio.repositories.DatabaseInitializer;

@SpringBootApplication
@ComponentScan(basePackages = {"com.prueba.comercio"} ) 
public class ProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductoApplication.class, args);
		
		DatabaseInitializer.initDB();
	}
}
