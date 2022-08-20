package com.ar.villaf.backendCourseManagmentSystem.exception;

import javax.persistence.EntityNotFoundException;

public class CourseNotFoundException extends EntityNotFoundException {
    public CourseNotFoundException(int id) {
        super("Course id: " + id + " not found");
    }
}
