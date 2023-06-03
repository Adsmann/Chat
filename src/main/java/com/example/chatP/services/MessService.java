package com.example.chatP.services;

import com.example.chatP.models.Chat;
import com.example.chatP.models.Mess;
import com.example.chatP.models.User;
import com.example.chatP.repositories.ChatRepository;
import com.example.chatP.repositories.MessRepository;
import com.example.chatP.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessService {
    private final MessRepository messRepository;
    private final UserRepository userRepository;


    public List<Mess> listChat(String name) {
        if (name != null) return messRepository.findByName(name);
        return messRepository.findAll();
    }


    public void saveMess(Principal principal, Mess mess) {
        mess.setUser(getUserByPrincipal(principal));
        log.info("{}", mess);
        messRepository.save(mess);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByName(principal.getName());
    }

    public void deleteMess(Long id) {
        messRepository.deleteById(id);
    }

    public Mess getChatByID(Long id){
        return messRepository.findById(id).orElse(null);
    }
}
