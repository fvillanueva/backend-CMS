package com.ar.villaf.backendCourseManagmentSystem.repository;

import com.ar.villaf.backendCourseManagmentSystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

     List<Course> findAllByOrderByNameAsc();

}
