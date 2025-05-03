package org.example.edujournalbackend.FaceAttend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class FaceAttendConfig implements WebSocketConfigurer {
    private final FaceAttendHandler faceAttendHandler;

    public FaceAttendConfig(FaceAttendHandler faceAttendHandler) {
        this.faceAttendHandler = faceAttendHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(faceAttendHandler, "/ws")
                .setAllowedOrigins("*");
    }
}
