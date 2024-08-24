package com.cors.cors_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
public class CorsClientApplication {


	@RequestMapping("/")
	public String home(){
		return "home";
	}
	public static void main(String[] args) {
		SpringApplication.run(CorsClientApplication.class, args);
	}

}
