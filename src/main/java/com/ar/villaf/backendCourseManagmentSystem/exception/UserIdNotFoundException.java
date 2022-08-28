package com.ar.villaf.backendCourseManagmentSystem.exception;

import javax.persistence.EntityNotFoundException;

public class UserIdNotFoundException extends EntityNotFoundException {
    public UserIdNotFoundException(int id){
        super("User with id " + id + " not found");
    }
}
