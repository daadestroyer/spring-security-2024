package com.thecoderstv.urlbased_and_rolebased_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/user/**").hasRole("USER")
                                .anyRequest().authenticated()  // Require authentication for all other URLs
                )
                .csrf(csrf -> csrf.disable())  // Disable CSRF protection for simplicity (consider enabling in production)
                .formLogin(formLogin ->
                        formLogin
                                .defaultSuccessUrl("/user/dashboard",true)
                                .permitAll() // here you are using spring provided default login page and we enabled it like this
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .permitAll()  // Allow unauthenticated access to logout
                );
//        logout
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login?logout")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .permitAll()  // Allow unauthenticated access to logout
//                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // created admin
        UserDetails admin = User.builder()
                .username("thecoderstv")
                .password(passwordEncoder().encode("thecoderstv"))
                .roles("ADMIN")
                .build();

        // created user
        UserDetails user = User.builder()
                .username("shubham")
                .password(passwordEncoder().encode("shubham"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);

    }

}
