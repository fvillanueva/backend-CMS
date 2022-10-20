package com.ar.villaf.backendCourseManagmentSystem;

import com.ar.villaf.backendCourseManagmentSystem.model.AppUser;
import com.ar.villaf.backendCourseManagmentSystem.model.Gender;
import com.ar.villaf.backendCourseManagmentSystem.model.Role;
import com.ar.villaf.backendCourseManagmentSystem.model.RoleName;
import com.ar.villaf.backendCourseManagmentSystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class BackendCourseManagmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendCourseManagmentSystemApplication.class, args);
	}

	@Bean
	CommandLineRunner run (UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		return  args -> {
			userService.saveRole(new Role(null, RoleName.ADMIN));
			userService.saveRole(new Role(null, RoleName.STUDENT));
			userService.saveRole(new Role(null, RoleName.TEACHER));

			userService.saveUser(new AppUser(null, "admin", bCryptPasswordEncoder.encode("pass123"), null,
					"Facundo", "Villanueva", Gender.MALE, "test@gmail.com",
					LocalDate.of(1997,11,19), "Argentina", null));
			userService.saveUser(new AppUser(null, "student", bCryptPasswordEncoder.encode("pass123"), null,
					"Student", "Student", Gender.MALE, "test@gmail.com",
					LocalDate.of(1997,11,19), "Argentina", null));
			userService.saveUser(new AppUser(null, "teacher", bCryptPasswordEncoder.encode("pass123"), null,
					"Teacher", "Teacher", Gender.MALE, "test@gmail.com",
					LocalDate.of(1997,11,19), "Argentina", null));

			userService.addRoleToUser("admin", RoleName.ADMIN);
			userService.addRoleToUser("admin", RoleName.TEACHER);
			userService.addRoleToUser("admin", RoleName.STUDENT);

			userService.addRoleToUser("student", RoleName.STUDENT);
			userService.addRoleToUser("teacher", RoleName.TEACHER);

		};
	}

}
