package com.ar.villaf.backendCourseManagmentSystem.controller;

import com.ar.villaf.backendCourseManagmentSystem.entity.MyUser;
import com.ar.villaf.backendCourseManagmentSystem.entity.Roles;
import com.ar.villaf.backendCourseManagmentSystem.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<MyUser>> findAllAdmins (){
        return ResponseEntity.ok(userService.findAllByRole(Roles.ADMIN));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MyUser> findAdmin (@PathVariable(value = "id") int id){
        return ResponseEntity.ok(userService.findById(id,Roles.ADMIN));
    }

}
