package com.example.tvordering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "com.example.tvordering")
public class TvorderingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TvorderingApplication.class, args);
	}

}
