package com.revature.dwte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DwteApplication {

	public static void main(String[] args) {

		SpringApplication.run(DwteApplication.class, args);
	}
	
	

}