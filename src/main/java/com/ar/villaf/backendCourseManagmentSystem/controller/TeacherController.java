package com.ar.villaf.backendCourseManagmentSystem.controller;

import com.ar.villaf.backendCourseManagmentSystem.entity.MyUser;
import com.ar.villaf.backendCourseManagmentSystem.entity.Roles;
import com.ar.villaf.backendCourseManagmentSystem.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/teacher", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeacherController {

    private final UserService userService;

    public TeacherController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<MyUser>> findAllTeachers (){
        return ResponseEntity.ok(userService.findAllByRole(Roles.STUDENT));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MyUser> saveTeacher(@RequestBody @Valid MyUser user){
        return ResponseEntity.ok(userService.saveUserRegistration(user,Roles.TEACHER));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MyUser> findTeacher (@PathVariable(value = "id") int id){
        return ResponseEntity.ok(userService.findById(id,Roles.TEACHER));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteTeacher (@PathVariable(value = "id") int id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("User with id " + id + "deleted");
    }

}
