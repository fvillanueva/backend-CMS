package com.ar.villaf.backendCourseManagmentSystem.serviceImpl;

import com.ar.villaf.backendCourseManagmentSystem.entity.MyUser;
import com.ar.villaf.backendCourseManagmentSystem.entity.Role;
import com.ar.villaf.backendCourseManagmentSystem.exception.RoleNotFoundException;
import com.ar.villaf.backendCourseManagmentSystem.exception.UserNotFoundException;
import com.ar.villaf.backendCourseManagmentSystem.repository.RoleRepository;
import com.ar.villaf.backendCourseManagmentSystem.repository.UserRepository;
import com.ar.villaf.backendCourseManagmentSystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<MyUser> findAllByRole(String roleName){
        Role role = roleRepository.findRoleByName(roleName).orElseThrow(() -> new RoleNotFoundException(roleName));
        return userRepository.findAll().stream()
                .filter(n -> n.getRoles().contains(role)).collect(Collectors.toList());
    }

    public MyUser findById (int id, String roleName) {
        Role role = roleRepository.findRoleByName(roleName).orElseThrow(() -> new RoleNotFoundException(roleName));
        return userRepository.findById(id).filter(u -> u.getRoles().contains(role)).orElseThrow(() -> new UserNotFoundException(id));
    }

    public MyUser saveUserRegistration (MyUser user, String roleName) {
        Role role = roleRepository.findRoleByName(roleName).
                orElseThrow(() -> new RoleNotFoundException(roleName));
        user.setRoles(List.of(role));
        return userRepository.save(user);
    }

    public void deleteUserById(int userId) {
        Optional<MyUser> user = userRepository.findById(userId);
        if(user.isEmpty())
            throw new UserNotFoundException(userId);
        userRepository.deleteById(userId);
    }


}
