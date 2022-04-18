package com.example.save.savepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SavepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SavepoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){return new RestTemplate();}
}
