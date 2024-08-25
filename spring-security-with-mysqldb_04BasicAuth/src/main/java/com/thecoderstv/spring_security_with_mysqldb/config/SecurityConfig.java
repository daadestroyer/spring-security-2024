package com.thecoderstv.spring_security_with_mysqldb.config;

import com.thecoderstv.spring_security_with_mysqldb.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // this is very important if you are working in localhost otherwise it will be a big mess
                .authorizeRequests(authorize ->
                        authorize
                                .requestMatchers("/public/**").permitAll()
                                .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                                .requestMatchers("/user/**").hasAnyRole("USER")

                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // here we are telling that we are enabling Basic Auth which and we are allowing accessing api from postman and we need to do basic authorization

        return http.build();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
