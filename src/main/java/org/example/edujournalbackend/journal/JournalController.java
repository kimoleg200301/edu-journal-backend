package org.example.edujournalbackend.journal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Журнал", description = "Операции для управления оценками и сессиями")
public class JournalController {
    private final JournalService journalService;
    private final RestTemplate restTemplate;

    @Autowired
    public JournalController(JournalService journalService, RestTemplate restTemplate) {
        this.journalService = journalService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/find_marks")
    @Operation(summary = "Оценки за месяц",
            description = "Возвращает оценки группы по предмету за месяц (YYYY-MM)")
    public List<Journal> findJournalByMonth(
            @Parameter(description = "ID группы", required = true)
            @RequestParam Long edu_group_id,
            @Parameter(description = "ID предмета", required = true)
            @RequestParam Long subject_id,
            @Parameter(description = "Дата в формате YYYY-MM", required = true)
            @RequestParam String date) {
        return journalService.findJournalByMonth(edu_group_id, subject_id, date);
    }

    @PostMapping("/start_session")
    @Operation(summary = "Старт/стоп сессии",
            description = "Запускает или останавливает сессию распознавания. flag=true — старт, false — стоп")
    public ResponseEntity<Map<String, String>> startSession(
            @Parameter(description = "ID группы", required = true)
            @RequestParam Long edu_group_id,
            @Parameter(description = "ID предмета", required = true)
            @RequestParam Long subject_id,
            @Parameter(description = "Дата занятия (YYYY-MM-DD)", required = true)
            @RequestParam String date,
            @Parameter(description = "Флаг старта (true) или остановки (false)", required = true)
            @RequestParam Boolean flag) {

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
    @Operation(summary = "Выставить/обновить оценки",
            description = "Принимает список оценок и сохраняет их для группы и предмета")
    public ResponseEntity<Map<String, String>> setMarks(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Список оценок", required = true)
            @RequestBody List<Journal> journals,
            @Parameter(description = "ID группы", required = true)
            @RequestParam Long edu_group_id,
            @Parameter(description = "ID предмета", required = true)
            @RequestParam Long subject_id) {
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
    @Operation(summary = "Удалить оценки",
            description = "Удаляет указанные оценки у студентов для предмета и группы")
    public ResponseEntity<String> deleteMarks(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Список оценок для удаления", required = true)
            @RequestBody List<Journal> journals,
            @Parameter(description = "ID группы", required = true)
            @RequestParam Long edu_group_id,
            @Parameter(description = "ID предмета", required = true)
            @RequestParam Long subject_id) {
        boolean isDeletedMarks = journalService.deleteMarks(journals, edu_group_id, subject_id);
        if (isDeletedMarks) {
            return ResponseEntity.ok("Оценки удалены!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Оценки не удалены!");
        }
    }
}
