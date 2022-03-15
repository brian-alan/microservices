package com.microservices.videogameservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class VideogameServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideogameServiceApplication.class, args);
	}

}
