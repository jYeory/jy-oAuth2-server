package com.jyeory.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jyeory")
public class JyOAuth2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JyOAuth2ServerApplication.class, args);
	}

}
