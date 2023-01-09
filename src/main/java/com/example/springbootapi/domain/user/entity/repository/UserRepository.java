package com.example.springbootapi.domain.user.entity.repository;

import com.example.springbootapi.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByCallNumber(String callNumber);
}
