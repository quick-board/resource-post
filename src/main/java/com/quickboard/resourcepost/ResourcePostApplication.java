package com.quickboard.resourcepost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ResourcePostApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourcePostApplication.class, args);
	}

}
