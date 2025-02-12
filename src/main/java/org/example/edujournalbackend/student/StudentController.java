package org.example.edujournalbackend.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        boolean isSaved = studentService.saveStudent(student);
        if (isSaved) {
            return ResponseEntity.ok("Студент сохранены!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Студент не сохранен!");
        }
    }
    @PutMapping("/update_student") // для обновления студента
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        boolean isUpdated = studentService.updateStudent(student);
        if (isUpdated) {
            return ResponseEntity.ok("Студент обновлен!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Студент не обновлен!");
        }
    }
    @DeleteMapping("/delete_student/{student_id}") // для удаления студента
    public ResponseEntity<String> deleteStudent(@PathVariable Long student_id) {
        boolean isDeleted = studentService.deleteStudent(student_id);
        if (isDeleted) {
            return ResponseEntity.ok("Студент удален!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Студент не удален!");
        }
    }
}
