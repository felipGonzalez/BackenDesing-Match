package com.uptc.desingMatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DesingMatchApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DesingMatchApplication.class, args);
	}

}
