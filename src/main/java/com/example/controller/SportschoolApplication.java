package com.example.controller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class SportschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportschoolApplication.class, args);
	}
}