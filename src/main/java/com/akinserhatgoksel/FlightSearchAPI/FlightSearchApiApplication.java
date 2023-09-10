package com.akinserhatgoksel.FlightSearchAPI;

import com.akinserhatgoksel.FlightSearchAPI.job.MockDataGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class FlightSearchApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightSearchApiApplication.class, args);
	}

	public CommandLineRunner initializeMockData(MockDataGenerator mockDataGenerator) {
		return args -> {
			mockDataGenerator.initializeMockData();
		};
	}
}
