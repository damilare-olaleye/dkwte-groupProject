package com.revature.dwte.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.revature.dwte.utility.ValidateUtil;

@Configuration
public class BeanConfig {

	@Bean
	public ValidateUtil valideateUtil() {
		return new ValidateUtil();
	}
}
