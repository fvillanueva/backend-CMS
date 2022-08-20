package com.ar.villaf.backendCourseManagmentSystem.repository;

import com.ar.villaf.backendCourseManagmentSystem.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Integer> {

    Optional<MyUser> findByUsername(String username);

}
