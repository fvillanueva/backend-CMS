package com.ar.villaf.backendCourseManagmentSystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.File;

@Entity
@Table(name = "course_video")
@Data
@NoArgsConstructor
@Slf4j
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
    @SuppressWarnings("unused")
    private void deleteVideoFromFolder () {
        File video = new File(this.videoPath);
        boolean delete = video.delete();
        if (!delete)
            log.error("Video: \"{}\" not found.", video.getName());
    }
}
