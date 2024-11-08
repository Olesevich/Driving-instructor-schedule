package com.example.instructor_log.repository;

import com.example.instructor_log.entity.MyUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUsers, Integer> {
    Optional<MyUsers> findByLogin(String login);


}
