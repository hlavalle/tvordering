package com.example.tvordering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.tvordering")
public class TvorderingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TvorderingApplication.class, args);
	}

}
