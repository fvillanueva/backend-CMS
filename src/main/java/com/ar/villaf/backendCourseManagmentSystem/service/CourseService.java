package com.ar.villaf.backendCourseManagmentSystem.service;

import com.ar.villaf.backendCourseManagmentSystem.entity.Course;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {

    List<Course> findAll ();

    Course findCourseById (int id);

    void deleteCourseById (int id);

    Course saveCourseRegistration(Course course);

    void uploadVideoToCourse(MultipartFile file, String videoName, int courseId);


}
