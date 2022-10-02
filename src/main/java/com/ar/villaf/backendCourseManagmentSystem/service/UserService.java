package com.ar.villaf.backendCourseManagmentSystem.service;

import com.ar.villaf.backendCourseManagmentSystem.model.AppUser;
import com.ar.villaf.backendCourseManagmentSystem.model.Role;
import com.ar.villaf.backendCourseManagmentSystem.exception.RoleNameNotFoundException;
import com.ar.villaf.backendCourseManagmentSystem.exception.UserIdNotFoundException;
import com.ar.villaf.backendCourseManagmentSystem.repository.RoleRepository;
import com.ar.villaf.backendCourseManagmentSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            log.error("User {} not found in the database", username);
            throw new UsernameNotFoundException("User " + username + " not found in the database");
        }else {
            log.info("User {} found in the database", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.get().getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName().toString())));
        return new User(user.get().getUsername(), user.get().getPassword(), authorities);
    }
    public List<AppUser> getUsersByRole(String roleName){
        log.info("Fetching users with role {}", roleName);
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RoleNameNotFoundException(roleName));
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

    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to the user {}", roleName, username);
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RoleNameNotFoundException(roleName));
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
