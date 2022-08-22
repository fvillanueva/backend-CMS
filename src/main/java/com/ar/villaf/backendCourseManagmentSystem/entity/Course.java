package com.ar.villaf.backendCourseManagmentSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Course name should not be blank")
    @Size(min = 3, message = "Course name should be at least 3 characters")
    @Size(max = 100, message = "Course name should be at max 100 characters")
    private String name;

    @NotBlank(message = "Course name should not be blank")
    @Size(min = 5, message = "Course name should be at least 5 characters")
    @Size(max = 300, message = "Course name should be at max 300 characters")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id")
    private List<Video> videos;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.REMOVE)
    private List<MyUser> users;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Comment> comments;


}
