package org.example.edujournalbackend.FaceAttend;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class FaceAttendHandler extends TextWebSocketHandler {
    private final FaceAttendDao faceAttendDao;

    public FaceAttendHandler(FaceAttendDao faceAttendDao) {
        this.faceAttendDao = faceAttendDao;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        faceAttendDao.addSession(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.println("📨 Получено: " + message.getPayload());

        faceAttendDao.broadcastMessage(message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        faceAttendDao.removeSession(session);
    }
}
