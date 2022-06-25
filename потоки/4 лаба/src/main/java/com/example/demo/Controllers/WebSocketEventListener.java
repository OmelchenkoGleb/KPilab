package com.example.demo.Controllers;

import com.example.demo.Models.ChatMessage;
import com.example.demo.Models.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;


import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class WebSocketEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    SimpMessageSendingOperations sendingOperations;

    @EventListener
    public void handleWebSocketConnectListener(final SessionConnectedEvent event){
        // Отправить сообщение о коннекте
        LOGGER.info("Connect !");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(final SessionConnectedEvent event){
        //Отправить сообщение о дисконекте
        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        //Достать юзера
        final String username = (String) headerAccessor.getSessionAttributes().get("username");

        //Привести в силу и отправить сообщение
        final ChatMessage chatMessage = ChatMessage.builder()
                .type(MessageType.DISCONNECT)
                .sender(username)
                .build();

        sendingOperations.convertAndSend("/topic/public", chatMessage);
    }
}
