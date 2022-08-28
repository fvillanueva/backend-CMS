package com.ar.villaf.backendCourseManagmentSystem.service;

import com.ar.villaf.backendCourseManagmentSystem.entity.AppUser;
import com.ar.villaf.backendCourseManagmentSystem.entity.Role;

import java.util.List;

public interface UserService {

    //TODO EVENTUALLY CHANGE TO RETURN A PAGE OF USERS
    List<AppUser> getUsersByRole(String role);
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    void deleteUserById(int userId);
    AppUser getUserById(int id);

}
