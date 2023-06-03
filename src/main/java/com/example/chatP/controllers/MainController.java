package com.example.chatP.controllers;

import com.example.chatP.models.Chat;
import com.example.chatP.models.Mess;
import com.example.chatP.models.User;
import com.example.chatP.repositories.ChatRepository;
import com.example.chatP.repositories.MessRepository;
import com.example.chatP.services.ChatService;
import com.example.chatP.services.MessService;
import com.example.chatP.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ChatService chatService;
    private final MessService messService;
    private final UserService userService;
    private final ChatRepository chatRepository;
    private final MessRepository messRepository;

    @GetMapping("/")
    public String startPage(@RequestParam(name = "title", required = false) String title, Principal principal, Model model){
        model.addAttribute("user", messService.getUserByPrincipal(principal));
        return "startpage";
    }

    @GetMapping("/setpassword")
    public String startPage(){
        return "setPassword";
    }

//    @GetMapping("/chat")
//    public String main(@RequestParam(name = "name", required = false) String name, Model model){
//        model.addAttribute("listchat", chatService.listChat(name));
//        return "start";
//    }

    @GetMapping("/chat")
    public String main(@RequestParam(name = "name", required = false)  String name, Long id, Model model, Chat chat, @AuthenticationPrincipal User user){
        model.addAttribute("listchat", chatService.listChat(name, id, chat));
        model.addAttribute("mainUser", user);
        return "start";
    }

    @GetMapping("/chat/{id}")
    public String chatInfo( @PathVariable Long id, Model model, @AuthenticationPrincipal User user){
        Chat chat = chatService.getChatByID(id);
        model.addAttribute("chat", chat);
        model.addAttribute("listmess", chat.getAllMessChat());
        model.addAttribute("mainUser", user);
        return "chat";
    }

    @PostMapping("/chat/{id}/create")
    public String createMess(Principal principal, Model model, Mess mess){
//        model.addAttribute("chat",chatService.getChatByID(id));
        messService.saveMess(principal, mess);
        return"redirect:/chat/{id}";
    }


    @PostMapping("/chat/create")
    public String createChat(Principal principal, Chat chat){
        chatService.saveChat(principal, chat);
        return"redirect:/chat";
    }
    @PostMapping("/chat/delete/{id}")
    public String deleteChat(@PathVariable Long id, Model model) {
        // Находим нужную запись по ID
        Chat chat = chatRepository.findById(id).orElseThrow();
        chatRepository.delete(chat); // Выполняем удаление

        // Возвращаем на главную страницу
        return "redirect:/chat";
    }

    @PostMapping("/chat/*/delete/{id}")
    public String deleteMess(@PathVariable Long id, Model model) {
        // Находим нужную запись по ID
        Mess mess = messRepository.findById(id).orElseThrow();
        messRepository.delete(mess); // Выполняем удаление
        Long id1 = mess.getChat().getId();
        // Возвращаем на главную страницу
        return "redirect:/chat";
    }

//    @PostMapping("/chat/delete/{id}")
//    public String deleteChat(@PathVariable Long id) {
//        chatService.deleteChat(id);
//        return "redirect:/chat";
//    }

//    @PostMapping("/chat/mess/delete/{id}")
//    public String deleteMess(@PathVariable Long id) {
//        messService.deleteMess(id);
//        return "redirect:/chat";
//    }
}
