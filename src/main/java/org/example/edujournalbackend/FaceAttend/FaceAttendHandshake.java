package org.example.edujournalbackend.FaceAttend;

import org.example.edujournalbackend.group.GroupService;
import org.example.edujournalbackend.student.Student;
import org.example.edujournalbackend.student.StudentService;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Component
public class FaceAttendHandshake implements HandshakeInterceptor {

    private final GroupService groupService;

    public FaceAttendHandshake(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {

        System.out.println(">>> beforeHandshake, URI = " + request.getURI());

        // Извлекаем servlet-запрос, чтобы работать с параметрами URL
        if (request instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest =
                    ((ServletServerHttpRequest) request).getServletRequest();

            String groupIdParam = servletRequest.getParameter("edu_group_id");
            System.out.println(">>> edu_group_id = " + groupIdParam);

            if (groupIdParam != null) {
                attributes.put("groupId", groupIdParam);
            }
        }
        return true;           // продолжить рукопожатие
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {
    }
}