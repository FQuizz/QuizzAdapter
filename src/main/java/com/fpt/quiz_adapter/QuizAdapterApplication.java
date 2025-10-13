package com.fpt.quiz_adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuizAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizAdapterApplication.class, args);
	}

}
