package com.ar.villaf.backendCourseManagmentSystem;

import com.ar.villaf.backendCourseManagmentSystem.entity.AppUser;
import com.ar.villaf.backendCourseManagmentSystem.entity.GenderConstants;
import com.ar.villaf.backendCourseManagmentSystem.entity.Role;
import com.ar.villaf.backendCourseManagmentSystem.entity.RolesConstants;
import com.ar.villaf.backendCourseManagmentSystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class BackendCourseManagmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendCourseManagmentSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner run (UserService userService) {
		return  args -> {
			userService.saveRole(new Role(null, RolesConstants.STUDENT));
			userService.saveRole(new Role(null, RolesConstants.ADMIN));
			userService.saveRole(new Role(null, RolesConstants.TEACHER));

			userService.saveUser(new AppUser(null, "admin", "pass123", null,
					"Facundo", "Villanueva", GenderConstants.MALE, "facundovillanueva97@gmail.com",
					LocalDate.of(1997,11,19), "Argentina", null));
			userService.saveUser(new AppUser(null, "student", "pass123", null,
					"Student", "Student", GenderConstants.MALE, "facundovillanueva97@gmail.com",
					LocalDate.of(1997,11,19), "Argentina", null));
			userService.saveUser(new AppUser(null, "teacher", "pass123", null,
					"Teacher", "Teacher", GenderConstants.MALE, "facundovillanueva97@gmail.com",
					LocalDate.of(1997,11,19), "Argentina", null));

			userService.addRoleToUser("admin", RolesConstants.ADMIN);
			userService.addRoleToUser("admin", RolesConstants.TEACHER);
			userService.addRoleToUser("admin", RolesConstants.STUDENT);

			userService.addRoleToUser("student", RolesConstants.STUDENT);
			userService.addRoleToUser("teacher", RolesConstants.TEACHER);

		};
	}
}
