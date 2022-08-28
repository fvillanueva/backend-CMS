package com.ar.villaf.backendCourseManagmentSystem.controller;

import com.ar.villaf.backendCourseManagmentSystem.entity.AppUser;
import com.ar.villaf.backendCourseManagmentSystem.entity.Roles;
import com.ar.villaf.backendCourseManagmentSystem.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<AppUser>> findAllUsers (){
        return ResponseEntity.ok(userService.getUsersByRole(Roles.STUDENT));
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<AppUser> findUser (@PathVariable(value = "id") int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUser> saveUser(@RequestBody @Valid AppUser user){
        return ResponseEntity.ok(userService.saveUser(user));
    }
    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<String> deleteUser (@PathVariable(value = "id") int id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("User with id " + id + " deleted");
    }

}
