package com.ar.villaf.backendCourseManagmentSystem.model;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "username should not be blank")
    @Size(min = 5, message = "username should be at least 6 characters")
    @Size(max = 15, message = "username should be at max 15 characters")
    private String username;

    @NotBlank(message = "password should not be blank")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

    @Column(name = "first_name")
    @NotBlank(message = "first name should not be blank")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "last name should not be blank")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Email
    @NotBlank(message = "email should not be blank")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    @PastOrPresent
    private LocalDate dateOfBirth;

    @Column(name = "country_of_birth")
    private String countryOfBirth;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Course> courses;

}
