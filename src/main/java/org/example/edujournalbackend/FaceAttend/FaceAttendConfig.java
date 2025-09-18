package org.example.edujournalbackend.FaceAttend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class FaceAttendConfig implements WebSocketConfigurer {
    private final FaceAttendHandler faceAttendHandler;
    private final FaceAttendHandshake faceAttendHandshake;

    public FaceAttendConfig(FaceAttendHandler faceAttendHandler, FaceAttendHandshake faceAttendHandshake) {
        this.faceAttendHandler = faceAttendHandler;
        this.faceAttendHandshake = faceAttendHandshake;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        // фронт
        registry.addHandler(faceAttendHandler, "/ws")
                .addInterceptors(faceAttendHandshake)
                .setAllowedOrigins("*");

        // flask
        registry.addHandler(faceAttendHandler, "/face-attend")
                .setAllowedOrigins("*");
    }
}
