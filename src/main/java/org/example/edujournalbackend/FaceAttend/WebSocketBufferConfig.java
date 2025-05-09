package org.example.edujournalbackend.FaceAttend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

@Configuration
public class WebSocketBufferConfig {

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(5 * 1024 * 1024);   // 5 MB для текстовых сообщений
        container.setMaxBinaryMessageBufferSize(5 * 1024 * 1024); // 5 MB для бинарных сообщений
        return container;
    }
}
