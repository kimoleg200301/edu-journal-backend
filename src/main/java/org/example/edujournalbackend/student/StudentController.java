package org.example.edujournalbackend.student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")
@Tag(name = "Студенты", description = "Операции для работы со студентами")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/info_student") // для отображения лк студента
    @Operation(summary = "Информация о студенте",
            description = "Возвращает информацию о студенте по идентификатору")
    public Optional<Student> getStudent(
            @Parameter(description = "ID студента", required = true)
            @RequestParam Long student_id) {
        return studentService.getStudentById(student_id);
    }

    @GetMapping("/") // для отображения списка студентов
    @Operation(summary = "Список студентов", description = "Возвращает всех студентов")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/save_student") // для добавления студента
    @Operation(summary = "Создать студента",
            description = "Создает нового студента",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Студент сохранен",
                            content = @Content(schema = @Schema(implementation = Map.class))),
                    @ApiResponse(responseCode = "500", description = "Ошибка сохранения")
            })
    public ResponseEntity<Map<String, String>> saveStudent(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные студента", required = true)
            @RequestBody Student student) {
        boolean isSaved = studentService.saveStudent(student);

        Map<String, String> response = new HashMap<>();

        if (isSaved) {
            response.put("message", "Студент сохранен!");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Студент не сохранен!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/update_student") // для обновления студента
    @Operation(summary = "Обновить студента", description = "Обновляет данные студента")
    public ResponseEntity<Map<String, String>> updateStudent(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Обновленные данные студента", required = true)
            @RequestBody Student student) {
        boolean isUpdated = studentService.updateStudent(student);

        Map<String, String> response = new HashMap<>();

        if (isUpdated) {
            response.put("message", "Студент обновлен!");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Студент не обновлен!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/delete_student/{student_id}") // для удаления студента
    @Operation(summary = "Удалить студента", description = "Удаляет студента по идентификатору")
    public ResponseEntity<Map<String, String>> deleteStudent(
            @Parameter(description = "ID студента", required = true)
            @PathVariable Long student_id) {
        boolean isDeleted = studentService.deleteStudent(student_id);

        Map<String, String> response = new HashMap<>();

        if (isDeleted) {
            response.put("message", "Студент удален!");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Студент не удален!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
