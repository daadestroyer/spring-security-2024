package com.thecoderstv.springboot_oauth2_googlelogin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/", "/login").permitAll()  // Allow unauthenticated access to root and login
                                .anyRequest().authenticated() // Require authentication for all other requests
                )
                .oauth2Login(Customizer.withDefaults())
                .logout(logout ->
                        logout.logoutSuccessUrl("/")
                );

        return http.build();
    }
}
