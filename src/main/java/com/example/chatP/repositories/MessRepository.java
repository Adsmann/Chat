package com.example.chatP.repositories;

import com.example.chatP.models.Chat;
import com.example.chatP.models.Mess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessRepository extends JpaRepository<Mess, Long> {
    List<Mess> findByName(String name);

}
