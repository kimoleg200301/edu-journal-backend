package org.example.edujournalbackend.FaceAttend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.edujournalbackend.group.GroupService;
import org.example.edujournalbackend.student.Student;
import org.example.edujournalbackend.student.StudentService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class FaceAttendHandler extends TextWebSocketHandler {
    private final FaceAttendDao faceAttendDao;
    private final StudentService studentService;
    private final GroupService groupService;

    public FaceAttendHandler(FaceAttendDao faceAttendDao, StudentService studentService, GroupService groupService) {
        this.faceAttendDao = faceAttendDao;
        this.studentService = studentService;
        this.groupService = groupService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        faceAttendDao.addSession(session);
//        String groupIdParam = (String) session.getAttributes().get("groupId");
//        List<Student> students = groupService.findAddedStudentsByGroupId(Long.parseLong(groupIdParam));
//        session.getAttributes().put("students", students);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        ObjectNode jsonNode = (ObjectNode) mapper.readTree(message.getPayload());

        Optional<Student> studentInfo = studentService.getStudentById(jsonNode.get("name").asLong());
        Student student = studentInfo.get();

        List<Student> students = (List<Student>) session.getAttributes().get("students");

//        boolean matched = students.stream()
//                .anyMatch(s -> s.getStudent_id().equals(student.getStudent_id()));
//
//        if (matched) {
            student.setImage(jsonNode.get("image").asText());

            System.out.println("📨 Получено: " + message.getPayload());
            System.out.println("Payload size: " + message.getPayloadLength());

            String studentInfoStr = mapper.writeValueAsString(student);

            faceAttendDao.broadcastMessage(studentInfoStr);
//        } else {
//            System.out.println("Студент не найден");
//        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        faceAttendDao.removeSession(session);
    }
}