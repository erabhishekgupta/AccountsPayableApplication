package com.application.apa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class AccountsPayableApplication {
	
	
	@Value("${spring.datasource.url:NOT SET}")
	private String dbUrl;

	public static void main(String[] args) {
		SpringApplication.run(AccountsPayableApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		System.out.println(">>> DB url from properties: " + dbUrl);
	}

}
