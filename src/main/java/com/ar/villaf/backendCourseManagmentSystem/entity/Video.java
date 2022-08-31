package com.ar.villaf.backendCourseManagmentSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "course_video")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "videoName", unique = true)
    private String name;

    @Column(name = "videoPath")
    private String videoPath;

    public Video(String name, String videoPath) {
        this.name = name;
        this.videoPath = videoPath;
    }

    @PreRemove
    private void deleteVideoFromFolder () {
        File video = new File(this.videoPath);
        video.delete();
    }
}
