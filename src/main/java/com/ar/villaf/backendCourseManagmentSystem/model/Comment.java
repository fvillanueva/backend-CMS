package com.ar.villaf.backendCourseManagmentSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course_comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "author")
    private String author;

    @Column(name = "comment")
    @NotBlank(message = "Comment should not be blank")
    @Size(min = 1, message = "Comment should be at least 1 characters")
    @Size(max = 300, message = "Comment should be at max 300 characters")
    private String comment;

    //THIS SHOULD PROB BE LAZILY FETCH
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();

}
