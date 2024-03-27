package com.team14.ibe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan
@Configuration
public class IbeApplication {
	public static void main(String[] args) {
		SpringApplication.run(IbeApplication.class, args);
	}
}
