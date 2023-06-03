package com.example.chatP.services;

import com.example.chatP.models.Chat;
import com.example.chatP.models.Mess;
import com.example.chatP.models.User;
import com.example.chatP.repositories.ChatRepository;
import com.example.chatP.repositories.MessRepository;
import com.example.chatP.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;


    public List<Chat> listChatAdmin(String name) {
        if (name != null) return chatRepository.findByName(name);

        return chatRepository.findAll();
    }
    public List<Chat> listChat(String name, Long id, Chat chat) {
        if (Objects.equals(chat.getIdUserOne(), id)){
            if (name != null) return chatRepository.findByName(name);
        }


        return chatRepository.findAll();
    }
    private Long getUserId(Principal principal) {
        String userString = principal.toString();
        int startIndex = userString.indexOf("id=");
        int endIndex = userString.indexOf(",", startIndex);
        return Long.valueOf(userString.substring(startIndex + 3, endIndex));
    }

    public void saveChat(Principal principal, Chat chat) {
        chat.setUser(getUserByPrincipal(principal));
        log.info("{}", chat);
        chatRepository.save(chat);
    }
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByName(principal.getName());
    }

    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }


    public Chat getChatByID(Long id){
        return chatRepository.findById(id).orElse(null);
    }
}
