package com.team14.ibe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@Configuration
@EnableJpaRepositories("com.team14.ibe.*")
public class IbeApplication {
	public static void main(String[] args) {
		SpringApplication.run(IbeApplication.class, args);
	}
}
