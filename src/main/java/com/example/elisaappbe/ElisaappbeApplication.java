package com.example.elisaappbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElisaappbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElisaappbeApplication.class, args);
	}

}
