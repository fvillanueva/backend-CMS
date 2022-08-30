package com.ar.villaf.backendCourseManagmentSystem.controller;

import com.ar.villaf.backendCourseManagmentSystem.config.CustomProperties;
import com.ar.villaf.backendCourseManagmentSystem.entity.Course;
import com.ar.villaf.backendCourseManagmentSystem.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/course", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> findAllCourses () {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Course> findCourse (@PathVariable(value = "id") int id){
        return ResponseEntity.ok(courseService.findCourseById(id));
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> saveCourse (@RequestBody @Valid Course course) {
        return ResponseEntity.ok(courseService.saveCourseRegistration(course));
    }

    @PostMapping(value = "{id}/video/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadVideo (@RequestParam("video")  MultipartFile video,
                                               @RequestParam("videoName")  String videoName,
                                               @PathVariable(value = "id") int courseId) {
        courseService.uploadVideoToCourse(video, videoName, courseId);
        return ResponseEntity.ok("Video upload successfully");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteCourse (@PathVariable(value = "id") int id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.ok("Course with id " + id + " deleted");
    }

}
