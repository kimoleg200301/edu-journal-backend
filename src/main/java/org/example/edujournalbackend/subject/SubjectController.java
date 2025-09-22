package org.example.edujournalbackend.subject;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/subjects")
@Tag(name = "Дисциплины", description = "Операции для работы с предметами")
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/")
    @Operation(summary = "Список дисциплин", description = "Возвращает список всех дисциплин")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/info_subject")
    @Operation(summary = "Информация о дисциплине", description = "Возвращает информацию о дисциплине по ID")
    public Optional<Subject> getSubject(
            @Parameter(description = "ID дисциплины", required = true)
            @RequestParam Long subject_id) {
        return subjectService.getBySubjectId(subject_id);
    }

    @PostMapping("/save_subject")
    @Operation(summary = "Создать дисциплину", description = "Создает новую дисциплину")
    public ResponseEntity<Map<String, String>> saveSubject(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные дисциплины", required = true)
            @RequestBody Subject subject) {
        boolean isSaved = subjectService.saveSubject(subject);

        Map<String, String> response = new HashMap<>();

        if (isSaved) {
            response.put("message", "Дисциплина сохранена!");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Дисциплина не сохранена!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/update_subject")
    @Operation(summary = "Обновить дисциплину", description = "Обновляет данные дисциплины")
    public ResponseEntity<Map<String, String>> updateSubject(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Обновленные данные дисциплины", required = true)
            @RequestBody Subject subject) {
        boolean isUpdated = subjectService.updateSubject(subject);

        Map<String, String> response = new HashMap<>();

        if (isUpdated) {
            response.put("message", "Дисциплина обновлена!");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Дисциплина обновлена!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/delete_subject/{subject_id}")
    @Operation(summary = "Удалить дисциплину", description = "Удаляет дисциплину по ID")
    public ResponseEntity<Map<String, String>> deleteSubject(
            @Parameter(description = "ID дисциплины", required = true)
            @PathVariable Long subject_id) {
        boolean isDeleted = subjectService.deleteSubject(subject_id);

        Map<String, String> response = new HashMap<>();

        if (isDeleted) {
            response.put("message", "Дисциплина удалена!");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Дисциплина не удалена!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
