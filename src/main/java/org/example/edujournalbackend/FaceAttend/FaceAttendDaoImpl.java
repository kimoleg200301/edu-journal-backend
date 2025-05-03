package org.example.edujournalbackend.FaceAttend;

import org.springframework.stereotype.Repository;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Repository
public class FaceAttendDaoImpl implements FaceAttendDao {
    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    public void addSession(WebSocketSession session) {
        this.sessions.add(session);
        System.out.println("✅ Добавлено соединение: " + session.getId());
    }

    @Override
    public void removeSession(WebSocketSession session) {
        this.sessions.remove(session);
        System.out.println("❌ Соединение удалено: " + session.getId());
    }

    @Override
    public void broadcastMessage(String message) {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(message));
                } catch (IOException e) {
                    System.out.println("Ошибка отправки сообщения: "  + e.getMessage());
                }
            }
        }
    }

    @Override
    public void sendMessageToSession(WebSocketSession session, String message) {
        if (session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                System.out.println("Ошибка отправки сообщения: "  + e.getMessage());
            }
        }
    }

    @Override
    public int getActiveSessionsCount() {
        return sessions.size();
    }
}
