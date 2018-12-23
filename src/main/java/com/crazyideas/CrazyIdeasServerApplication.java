package com.crazyideas;

import com.crazyideas.models.Role;
import com.crazyideas.models.Thinker;
import com.crazyideas.repositories.RoleRepository;
import com.crazyideas.repositories.ThinkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class CrazyIdeasServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrazyIdeasServerApplication.class, args);
	}

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Bean
	CommandLineRunner init(RoleRepository roleRepository, ThinkerRepository thinkerRepository) {

		return args -> {

			Role adminRole = roleRepository.findByRole("ADMIN");
			if (adminRole == null) {
				Role newAdminRole = new Role();
				newAdminRole.setRole("ADMIN");
				roleRepository.save(newAdminRole);
			}

			Role userRole = roleRepository.findByRole("USER");
			if (userRole == null) {
				Role newUserRole = new Role();
				newUserRole.setRole("USER");
				roleRepository.save(newUserRole);
			}
		};

	}

}

