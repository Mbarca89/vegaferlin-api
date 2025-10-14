package com.mbarca.VegaFerlin;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.ZoneId;
import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class VegaFerlinApplication {

	public static void main(String[] args) {
		SpringApplication.run(VegaFerlinApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// Forzar zona horaria global de la app
		TimeZone.setDefault(TimeZone.getTimeZone("America/Argentina/Buenos_Aires"));
		System.out.println("✅ TimeZone set to: " + TimeZone.getDefault().getID());
		System.out.println("🕒 System default ZoneId: " + ZoneId.systemDefault());
		System.out.println("🕓 LocalDateTime.now(): " + java.time.LocalDateTime.now());
	}
}