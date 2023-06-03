package com.example.chatP.controllers;


import com.example.chatP.models.User;
import com.example.chatP.repositories.UserRepository;
import com.example.chatP.services.ChatService;
import com.example.chatP.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final ChatService chatService;
    private final UserRepository userRepository;

    @GetMapping("/admin")
    public String admin(Model model){
        return "admin";
    }

    @GetMapping("/admin/users")
    public String adminUserList(Model model){
        model.addAttribute("users", userService.list());
        return "adminUserList";
    }

    @GetMapping("/admin/chats")
    public String adminChatList(Model model, String name){
        model.addAttribute("listchat", chatService.listChatAdmin(name));
        return "adminChatList";
    }

    @GetMapping("/admin/user/{id}")
    public String adminUser(Model model, String name, @PathVariable Long id){
        User user = userService.getUserByID(id);
        model.addAttribute("listchat", chatService.listChatAdmin(name));
        model.addAttribute("users", userService.list());
        return "adminUser";
    }

    @PostMapping("/admin/user/delete/{id}")
    public String deleteMess(@PathVariable Long id, Model model) {
        // Находим нужную запись по ID
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user); // Выполняем удаление

        // Возвращаем на главную страницу
        return "redirect:/admin/users";
    }






}

















