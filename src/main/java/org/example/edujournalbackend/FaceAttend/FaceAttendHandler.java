package org.example.edujournalbackend.FaceAttend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.edujournalbackend.student.Student;
import org.example.edujournalbackend.student.StudentService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Optional;

@Component
public class FaceAttendHandler extends TextWebSocketHandler {
    private final FaceAttendDao faceAttendDao;
    private final StudentService studentService;

    public FaceAttendHandler(FaceAttendDao faceAttendDao, StudentService studentService) {
        this.faceAttendDao = faceAttendDao;
        this.studentService = studentService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        faceAttendDao.addSession(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws JsonProcessingException {
        ObjectMapper objectMapped = new ObjectMapper();
        objectMapped.registerModule(new JavaTimeModule());

        ObjectNode jsonNode = (ObjectNode) objectMapped.readTree(message.getPayload());

        Optional<Student> studentInfo = studentService.getStudentById(jsonNode.get("name").asLong());
        Student student = studentInfo.get();

        student.setImage(jsonNode.get("image").asText());

        System.out.println("📨 Получено: " + message.getPayload());
        System.out.println("Payload size: " + message.getPayloadLength());

        faceAttendDao.broadcastMessage(objectMapped.writeValueAsString(student));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        faceAttendDao.removeSession(session);
    }
}
