package com.epam.finaltask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.epam.finaltask")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
