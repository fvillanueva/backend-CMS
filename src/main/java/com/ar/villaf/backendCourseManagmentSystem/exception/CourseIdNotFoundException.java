package com.ar.villaf.backendCourseManagmentSystem.exception;

import javax.persistence.EntityNotFoundException;

public class CourseIdNotFoundException extends EntityNotFoundException {
    public CourseIdNotFoundException(int id) {
        super("Course id: " + id + " not found");
    }
}
