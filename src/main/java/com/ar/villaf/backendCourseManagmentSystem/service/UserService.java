package com.ar.villaf.backendCourseManagmentSystem.service;

import com.ar.villaf.backendCourseManagmentSystem.exception.RoleNameNotFoundException;
import com.ar.villaf.backendCourseManagmentSystem.exception.UserIdNotFoundException;
import com.ar.villaf.backendCourseManagmentSystem.model.AppUser;
import com.ar.villaf.backendCourseManagmentSystem.model.Role;
import com.ar.villaf.backendCourseManagmentSystem.model.RoleName;
import com.ar.villaf.backendCourseManagmentSystem.repository.RoleRepository;
import com.ar.villaf.backendCourseManagmentSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public List<AppUser> getUsersByRole(RoleName roleName){
        log.info("Fetching users with role {}", roleName);
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RoleNameNotFoundException(roleName.toString()));
        return userRepository.findAll().stream()
                .filter(n -> n.getRoles().contains(role)).collect(Collectors.toList());
    }

    public AppUser getUserById(int id) {
        log.info("Fetching user with id {}", id);
        return userRepository.findById(id).orElseThrow(() -> new UserIdNotFoundException(id));
    }

    public AppUser saveUser(AppUser user) {
        log.info("Saving new user {} to the database", user.getUsername());
        return userRepository.save(user);
    }

    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    public void addRoleToUser(String username, RoleName roleName) {
        log.info("Adding role {} to the user {}", roleName, username);
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RoleNameNotFoundException(roleName.toString()));
        user.getRoles().add(role);
    }

    public void deleteUserById(int userId) {
        log.info("Deleting user with id {}", userId);
        Optional<AppUser> user = userRepository.findById(userId);
        if(user.isEmpty())
            throw new UserIdNotFoundException(userId);
        userRepository.deleteById(userId);
    }


}
