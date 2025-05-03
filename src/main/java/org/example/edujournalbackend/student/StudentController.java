package org.example.edujournalbackend.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/info_student") // для отображения лк студента
    public Optional<Student> getStudent(@RequestParam Long student_id) {
        return studentService.getStudentById(student_id);
    }

    @GetMapping("/") // для отображения списка студентов
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/save_student") // для добавления студента
    public ResponseEntity<Map<String, String>> saveStudent(@RequestBody Student student) {
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
    public ResponseEntity<Map<String, String>> updateStudent(@RequestBody Student student) {
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
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable Long student_id) {
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
