package com.example.chatP.services;

import com.example.chatP.models.Mess;
import com.example.chatP.models.User;
import com.example.chatP.models.enums.Role;
import com.example.chatP.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user){
        UUID uuid = UUID.randomUUID();
        String name = user.getName();
        if (userRepository.findByName(user.getName()) != null) return false;
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setLogin(uuid.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new User with name: {}", name);
        userRepository.save(user);
        return true;
    }

    public List<User> list(){
        return userRepository.findAll();
    }


    public User getUserByID(Long id){
        return userRepository.findById(id).orElse(null);
    }




}
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        User user = userRepository.findByName(name);
//        if(user == null) {throw new UsernameNotFoundException("No user found with name");}
////        List<String> roles = Array.asList(user.getRole());
//        UserDetails userDetails =
//                org.springframework.security.core.userdetails.User.builder()
//                        .username(user.getName())
//                        .password(user.getPassword())
////                        .roles(user.getRoles(Role_User))
//                        .build();
//        return userDetails;
//
//    }