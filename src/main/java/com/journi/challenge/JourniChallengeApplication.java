package com.journi.challenge;

import com.journi.challenge.configuration.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AppConfig.class)
public class JourniChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JourniChallengeApplication.class, args);
	}

}
