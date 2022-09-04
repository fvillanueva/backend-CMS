package com.ar.villaf.backendCourseManagmentSystem.service;

import com.ar.villaf.backendCourseManagmentSystem.utils.CustomProperties;
import com.ar.villaf.backendCourseManagmentSystem.entity.Course;
import com.ar.villaf.backendCourseManagmentSystem.entity.Video;
import com.ar.villaf.backendCourseManagmentSystem.exception.CourseIdNotFoundException;
import com.ar.villaf.backendCourseManagmentSystem.exception.InvalidFormatException;
import com.ar.villaf.backendCourseManagmentSystem.exception.VideoNameDuplicatedException;
import com.ar.villaf.backendCourseManagmentSystem.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CustomProperties customProperties;

    public List<Course> findAll () {
        log.info("Fetching all courses");
        return courseRepository.findAll();
    }

    public Course findCourseById (int id) {
        log.info("Fetching course with id {}", id);
        return courseRepository.findById(id).orElseThrow(() -> new CourseIdNotFoundException(id));
    }

    public void deleteCourseById(int id) {
        log.info("Deleting course with id {}", id);
        Optional<Course> course = courseRepository.findById(id);
        if(course.isEmpty())
            throw new CourseIdNotFoundException(id);
        courseRepository.deleteById(id);
        try {
            FileUtils.deleteDirectory(new File(customProperties.getVideosPath()+"/"+id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Course saveCourseRegistration(Course course) {
        log.info("Saving course {} to the database", course.getName());
        return courseRepository.save(course);
    }

    public void uploadVideoToCourse(MultipartFile video, String videoName, int courseId){
        Course course = findCourseById(courseId);
        validateVideo(video, course, videoName);
        log.info("Uploading video {} to the couse {}", videoName, course.getName());
        String pathStr = customProperties.getVideosPath()+courseId+"/"+video.getOriginalFilename();
        File path = new File(pathStr);
        try {
            video.transferTo(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        course.getVideos().add(new Video(videoName, pathStr));
        courseRepository.save(course);
    }

    private void validateVideo (MultipartFile video, Course course, String videoName){
        if (!Objects.equals(video.getContentType(), "video/mp4")) {
            throw new InvalidFormatException(video.getContentType());
        }
        if (course.getVideos().stream().anyMatch(v -> v.getName().equals(videoName))) {
            throw new VideoNameDuplicatedException(videoName);
        }
    }
}
