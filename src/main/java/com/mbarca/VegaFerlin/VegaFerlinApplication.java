package com.mbarca.VegaFerlin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VegaFerlinApplication {
	public static void main(String[] args) {
		SpringApplication.run(VegaFerlinApplication.class, args);
	}
}
