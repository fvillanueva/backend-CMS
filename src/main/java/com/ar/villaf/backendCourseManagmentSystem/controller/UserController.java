package com.ar.villaf.backendCourseManagmentSystem.controller;

import com.ar.villaf.backendCourseManagmentSystem.entity.AppUser;
import com.ar.villaf.backendCourseManagmentSystem.entity.Role;
import com.ar.villaf.backendCourseManagmentSystem.entity.RolesConstants;
import com.ar.villaf.backendCourseManagmentSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(path = "/users/students")
    public ResponseEntity<List<AppUser>> findAllStudents (){
        return ResponseEntity.ok(userService.getUsersByRole(RolesConstants.STUDENT));
    }
    @GetMapping(path = "/users/teachers")
    public ResponseEntity<List<AppUser>> findAllTeachers (){
        return ResponseEntity.ok(userService.getUsersByRole(RolesConstants.TEACHER));
    }
    @GetMapping(path = "/users/admins")
    public ResponseEntity<List<AppUser>> findAllAdmins (){
        return ResponseEntity.ok(userService.getUsersByRole(RolesConstants.ADMIN));
    }
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<AppUser> findUser (@PathVariable(value = "id") int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PostMapping(path = "/user/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUser> saveUser(@RequestBody @Valid AppUser user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PostMapping(path = "/role/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> saveRole(@RequestBody @Valid Role role){
        return ResponseEntity.ok(userService.saveRole(role));
    }

    @PostMapping(path = "/role/addToUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addRoleToUser(String roleName, String username){
        userService.addRoleToUser(username,roleName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/users/delete/{id}")
    public ResponseEntity<String> deleteUser (@PathVariable(value = "id") int id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("User with id " + id + " deleted");
    }

}
