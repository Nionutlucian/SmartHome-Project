package com.smarthome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.smarthome.model"})
@ComponentScan({"com.smarthome.controller","com.smarthome.service","resources","com.smarthome.utility"})
@EnableJpaRepositories("com.smarthome.repository")
@EnableScheduling
public class SmarthomeBackendApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SmarthomeBackendApplication.class, args);
	}

}
