package com.ar.villaf.backendCourseManagmentSystem.service;

import com.ar.villaf.backendCourseManagmentSystem.model.SecurityAppUser;
import com.ar.villaf.backendCourseManagmentSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(SecurityAppUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
