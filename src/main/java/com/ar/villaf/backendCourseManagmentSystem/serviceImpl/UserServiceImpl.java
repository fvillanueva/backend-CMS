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

    public List<MyUser> getUsersByRole(String roleName){
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RoleNotFoundException(roleName));
        return userRepository.findAll().stream()
                .filter(n -> n.getRoles().contains(role)).collect(Collectors.toList());
    }

    public MyUser getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public MyUser saveUser(MyUser user) {
        return userRepository.save(user);
    }

    public Role saveRole(Role role) {
        return null;
    }

    public void addRoleToUser(String username, String roleName) {

    }

    public void deleteUserById(int userId) {
        Optional<MyUser> user = userRepository.findById(userId);
        if(user.isEmpty())
            throw new UserNotFoundException(userId);
        userRepository.deleteById(userId);
    }


}
