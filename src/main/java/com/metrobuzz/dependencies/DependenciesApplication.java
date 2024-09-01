package com.metrobuzz.dependencies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DependenciesApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
        System.setProperty("DATABASE_URI", dotenv.get("DATABASE_URI"));
		SpringApplication.run(DependenciesApplication.class, args);
	}
}
