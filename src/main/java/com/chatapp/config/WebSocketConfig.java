package com.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Set up the path to a websocket channel
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Clients subscribe to /topic/... to receive messages
        config.enableSimpleBroker("/topic");
        // Messages sent from client start with /app
        config.setApplicationDestinationPrefixes("/app");
    }

    // Set up the webscocket connection
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket endpoint, with SockJS fallback
        registry.addEndpoint("/ws").withSockJS();
    }
}
