package com.ar.villaf.backendCourseManagmentSystem.exception;

import javax.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException (int id){
        super("User with id " + id + " not found");
    }
}
