package com.example.demo.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageConfig implements WebSocketMessageBrokerConfigurer {

    // регистрация точек (проход в реестр конечных точек стомп)
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // сохранение реестра в конечной точке
        registry.addEndpoint("/chat-example").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // префикс для приложения
        registry.setApplicationDestinationPrefixes("/app");
        // сообщения брокеру из реестра (всё это будет иметь префикс с этой темой)
        registry.enableSimpleBroker("/topic");
    }
}
