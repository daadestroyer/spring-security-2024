package com.cors.cors_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RestController
// @CrossOrigin(origins = "*") // it will allow all origin now because we have disable cross origin
public class CorsAppApplication {

    //    @CrossOrigin(origins = "http://localhost:9090") // this is method level what is request come to any other controller thats why better to do at global level
    @GetMapping("/access")
    public String access() {
        return "Welcome to TheCoders TV Portal";
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("http://localhost:9090");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(CorsAppApplication.class, args);
    }

}
