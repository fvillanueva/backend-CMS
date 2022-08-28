package com.ar.villaf.backendCourseManagmentSystem.exception;

import javax.persistence.EntityNotFoundException;

public class RoleNameNotFoundException extends EntityNotFoundException {
    public RoleNameNotFoundException(String role) {
        super("Role " + role + " not found");
    }
}
