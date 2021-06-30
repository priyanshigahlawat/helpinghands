package com.example.HelpingHands;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaRepositories
@EnableSwagger2
@CrossOrigin(origins = "*")
public class HelpingHandsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpingHandsApplication.class, args);
	}

}