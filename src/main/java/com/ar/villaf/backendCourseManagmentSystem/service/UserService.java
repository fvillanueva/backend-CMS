package com.ar.villaf.backendCourseManagmentSystem.service;

import com.ar.villaf.backendCourseManagmentSystem.entity.MyUser;
import com.ar.villaf.backendCourseManagmentSystem.entity.Role;

import java.util.List;

public interface UserService {

    List<MyUser> getUsersByRole(String role);
    MyUser saveUser(MyUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    void deleteUserById(int userId);
    MyUser getUserById(int id);

}
