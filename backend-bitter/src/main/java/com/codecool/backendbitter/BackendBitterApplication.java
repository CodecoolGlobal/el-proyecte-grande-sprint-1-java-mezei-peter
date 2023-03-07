package com.codecool.backendbitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BackendBitterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendBitterApplication.class, args);
	}

}
