package com.ar.villaf.backendCourseManagmentSystem.serviceImpl;

import com.ar.villaf.backendCourseManagmentSystem.config.CustomProperties;
import com.ar.villaf.backendCourseManagmentSystem.entity.Course;
import com.ar.villaf.backendCourseManagmentSystem.entity.Video;
import com.ar.villaf.backendCourseManagmentSystem.exception.CourseNotFoundException;
import com.ar.villaf.backendCourseManagmentSystem.exception.InvalidFormatException;
import com.ar.villaf.backendCourseManagmentSystem.exception.VideoAlreadyExistException;
import com.ar.villaf.backendCourseManagmentSystem.repository.CourseRepository;
import com.ar.villaf.backendCourseManagmentSystem.service.CourseService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CustomProperties customProperties;

    public CourseServiceImpl(CourseRepository courseRepository, CustomProperties customProperties) {
        this.courseRepository = courseRepository;
        this.customProperties = customProperties;
    }

    public List<Course> findAll () {
        return courseRepository.findAll();
    }

    public Course findCourseById (int id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
    }

    public void deleteCourseById(int id) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isEmpty())
            throw new CourseNotFoundException(id);
        courseRepository.deleteById(id);
        try {
            FileUtils.deleteDirectory(new File(customProperties.getVideosPath()+"/"+id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Course saveCourseRegistration(Course course) {
        return courseRepository.save(course);
    }

    public void uploadVideoToCourse(MultipartFile video, String videoName, int courseId){
        Course course = findCourseById(courseId);
        validateVideo(video, course, videoName);
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
        if (course.getVideos().stream().filter(v -> v.getName().equals(videoName)).findAny().isPresent()) {
            throw new VideoAlreadyExistException(videoName);
        }
    }
}
