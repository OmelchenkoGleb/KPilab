package com.example.demo.Controllers;

import com.example.demo.Models.ChatMessage;
import com.example.demo.Models.admin;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ChatController {

    // Возращает сообщение чата
    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload final ChatMessage chatMessage){
        return chatMessage;
    }

    // Добавляет нового пользователя в сеанс
    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload final ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        // получить атрибуты сеанса и вставить пользователя при этом отправить сообщение
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @GetMapping("/chatonline")
    public String chatonline(Model model){
        admin admin = (admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(admin.getUsername());
        model.addAttribute("username",admin.getUsername());
        return "/chat/index";
    }

}
