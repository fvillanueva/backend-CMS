package com.ar.villaf.backendCourseManagmentSystem;

import com.ar.villaf.backendCourseManagmentSystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendCourseManagmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendCourseManagmentSystemApplication.class, args);
	}

	CommandLineRunner run (UserService userService) {
		return  args -> {

		};
	}
}
