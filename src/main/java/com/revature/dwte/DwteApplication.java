package com.revature.dwte;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DwteApplication {
	private static Logger logger = LoggerFactory.getLogger(DwteApplication.class);

	public static void main(String[] args) {

		logger.info("userLogin(username, password)");

		SpringApplication.run(DwteApplication.class, args);
	}

}
