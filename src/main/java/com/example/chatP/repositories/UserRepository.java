package com.example.chatP.repositories;

import com.example.chatP.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);


    User findUserById(Long id);
}
