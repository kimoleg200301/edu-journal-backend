package org.example.edujournalbackend.FaceAttend;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

public interface FaceAttendDao {
    void addSession(WebSocketSession session);
    void removeSession(WebSocketSession session);
    void broadcastMessage(String message);
    void sendMessageToSession(WebSocketSession session, String message);
    int getActiveSessionsCount();
}
