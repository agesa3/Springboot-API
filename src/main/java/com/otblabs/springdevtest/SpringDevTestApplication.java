package com.otblabs.springdevtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication

public class SpringDevTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDevTestApplication.class, args);
	}

	// TODO : Add BCryptPasswordEncoder bean here
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
