package com.codecool.backendbitter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BackendBitterApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendBitterApplication.class, args);
	}

        @Override
        public void run(String... args) throws Exception {
            // possible initialization code here (if it is done via beans
            // then it can even differ profile-by-profile)
        }
}
