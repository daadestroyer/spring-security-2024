package com.thecoderstv.spring_security_db_example;

import com.thecoderstv.spring_security_db_example.entity.User;
import com.thecoderstv.spring_security_db_example.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringSecurityDbExampleApplication implements CommandLineRunner {


    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

	public SpringSecurityDbExampleApplication(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDbExampleApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.findByUsername("admin").isEmpty()) {
			User admin = new User();
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("admin123"));
			admin.setRole("ROLE_ADMIN");
			userRepository.save(admin);
		}

		if (userRepository.findByUsername("user").isEmpty()) {
			User user = new User();
			user.setUsername("user");
			user.setPassword(passwordEncoder.encode("password"));
			user.setRole("ROLE_USER");
			userRepository.save(user);
		}
	}
}
