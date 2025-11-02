package com.sports.Sports;

import com.sports.Sports.Repository.RoleRepository;
import com.sports.Sports.Repository.UserRepository;
import com.sports.Sports.model.Role;
import com.sports.Sports.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SportsApplication {

	public static void main(String[] args) {

		SpringApplication.run(SportsApplication.class, args);

	}





}
