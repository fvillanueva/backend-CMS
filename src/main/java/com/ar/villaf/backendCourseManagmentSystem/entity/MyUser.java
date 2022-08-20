package com.ar.villaf.backendCourseManagmentSystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "app_user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Data
@NoArgsConstructor
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "username should not be blank")
    @Size(min = 6, message = "username should be at least 6 characters")
    @Size(max = 15, message = "username should be at max 15 characters")
    private String username;

    @NotBlank(message = "password should not be blank")
    @Size(min = 6, message = "password should be at least 6 characters")
    @Size(max = 15, message = "password should be at max 15 characters")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    private Collection<Role> roles;

    @Column(name = "first_name")
    @NotBlank(message = "first name should not be blank")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "last name should not be blank")
    private String lastName;

    private String gender;

    @Email
    @NotBlank(message = "email should not be blank")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    @PastOrPresent
    @NotBlank(message = "Date of birth should not be blank")
    private LocalDate dateOfBirth;

    @Column(name = "country_of_birth")
    private String countryOfBirth;

    @ManyToMany
    @JoinTable(
            name = "user_course",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )

    private Set<Course> courses;

}
