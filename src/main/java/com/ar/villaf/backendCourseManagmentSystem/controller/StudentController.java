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
@RequestMapping(path = "/api/v1/student", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    private final UserService userService;

    public StudentController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<MyUser>> findAllStudents (){
        return ResponseEntity.ok(userService.findAllByRole(Roles.STUDENT));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MyUser> findStudent (@PathVariable(value = "id") int id){
        return ResponseEntity.ok(userService.findById(id,Roles.STUDENT));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MyUser> saveStudent(@RequestBody @Valid MyUser user){
        return ResponseEntity.ok(userService.saveUserRegistration(user,Roles.STUDENT));
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteStudent (@PathVariable(value = "id") int id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("User with id " + id + " deleted");
    }


}
