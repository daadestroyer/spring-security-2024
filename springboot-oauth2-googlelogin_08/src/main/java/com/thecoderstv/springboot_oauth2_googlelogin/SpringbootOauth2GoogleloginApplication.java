package com.thecoderstv.springboot_oauth2_googlelogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@RestController
public class SpringbootOauth2GoogleloginApplication {
 // https://console.cloud.google.com/apis/credentials?project=imposing-volt-435611-m0
	@GetMapping("/welcome")
	public String welcome(){
		return "Welcome to google";
	}
	@GetMapping("/user")
	public Principal user(Principal principal){
		System.out.println("username : "+principal.getName());
		return principal;
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootOauth2GoogleloginApplication.class, args);
	}

}
