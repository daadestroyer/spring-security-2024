package com.thecoderstv.urlbased_and_rolebased_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
public class UrlbasedAndRolebasedSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlbasedAndRolebasedSecurityApplication.class, args);
	}

}
