package org.example.edujournalbackend.journal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/journals")
public class JournalController {
    private final JournalService journalService;
    private final RestTemplate restTemplate;

    @Autowired
    public JournalController(JournalService journalService, RestTemplate restTemplate) {
        this.journalService = journalService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/find_marks")
    public List<Journal> findJournalByMonth(@RequestParam Long edu_group_id, @RequestParam Long subject_id, @RequestParam String date) {
        return journalService.findJournalByMonth(edu_group_id, subject_id, date);
    }

    @PostMapping("/start_session")
    public ResponseEntity<Map<String, String>> startSession(@RequestParam Long edu_group_id, @RequestParam Long subject_id, @RequestParam String date, @RequestParam Boolean flag) {

        String flaskUrl = flag
                ? "http://localhost:5000/start"
                : "http://localhost:5000/stop";

        Map<String, String> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        ResponseEntity<String> flaskResponse;
        String responseBody;
        JsonNode rootNode = null;

        try {
            flaskResponse = restTemplate.postForEntity(flaskUrl, null, String.class);
            responseBody = flaskResponse.getBody();
            rootNode = objectMapper.readTree(responseBody);

            if (flaskResponse.getStatusCode().is2xxSuccessful()) {
                String status = rootNode.get("status").asText();

                switch (status) {
                    case "started":
                        response.put("message", "Сессия запущена");
                        break;
                    case "stopping":
                        response.put("message", "Сессия завершена");
                        break;
                    default:
                        response.put("message", "Неполадки на стороне системы распознавания: " + status);
                }
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Ошибка Flask");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (Exception e) {
            if (rootNode != null && rootNode.has("status")) {
                String status = rootNode.get("status").asText();

                switch (status) {
                    case "already running":
                        response.put("message", "Сессия уже запущена пользователем");
                        break;
                    case "not running":
                        response.put("message", "Сессия ещё не начата");
                        break;
                    default:
                        response.put("message", "Ошибка при обращении к Flask-серверу: " + e.getMessage());
                }
            } else {
                response.put("message", "Сервер распознавания не запущен");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/set_marks")
    public ResponseEntity<Map<String, String>> setMarks(@RequestBody List<Journal> journals, @RequestParam Long edu_group_id, @RequestParam Long subject_id) {
        boolean isUpdatedMarks = journalService.setMarks(journals, edu_group_id, subject_id);

        Map<String, String> response = new HashMap<>();

        if (isUpdatedMarks) {
            response.put("message", "Оценки обновлены/выставлены!");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Оценки не обновлены/выставлены!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/delete_marks")
    public ResponseEntity<String> deleteMarks(@RequestBody List<Journal> journals, @RequestParam Long edu_group_id, @RequestParam Long subject_id) {
        boolean isDeletedMarks = journalService.deleteMarks(journals, edu_group_id, subject_id);
        if (isDeletedMarks) {
            return ResponseEntity.ok("Оценки удалены!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Оценки не удалены!");
        }
    }
}
