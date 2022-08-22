package com.ar.villaf.backendCourseManagmentSystem.service;

import com.ar.villaf.backendCourseManagmentSystem.entity.MyUser;

import java.util.List;

public interface UserService {

    List<MyUser> findAllByRole(String role);
    MyUser saveUserRegistration(MyUser user, String roleName);
    void deleteUserById(int userId);
    MyUser findByIdAndRole (int id, String role);
    MyUser findByUsername (String username);
}
