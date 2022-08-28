package com.ar.villaf.backendCourseManagmentSystem.repository;

import com.ar.villaf.backendCourseManagmentSystem.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);

}
