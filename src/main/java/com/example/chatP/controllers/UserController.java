package com.example.chatP.controllers;

import com.example.chatP.models.Mess;
import com.example.chatP.models.User;
import com.example.chatP.repositories.UserRepository;
import com.example.chatP.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.lang.model.element.Name;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/*/hello")
    public String securityUrl() {
        return "hello";
    }

    // Отслеживаем POST данные по определенному URL адресу
    @PostMapping("/usersetpassword/{id}")
    public String updateData(@AuthenticationPrincipal User user, @RequestParam String password, Model model) {
        // Находим нужную запись по ID
        User test = userRepository.findById(user.getId()).orElseThrow();
        test.setPassword(password); // Устанавливаем новое значение
        userRepository.save(test); // Сохраняем (обновляем) запись
        // Возвращаем на главную страницу
        return "redirect:/";
    }


}
