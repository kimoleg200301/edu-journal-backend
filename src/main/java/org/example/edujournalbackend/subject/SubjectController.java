package org.example.edujournalbackend.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {
    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/info_subject")
    public Optional<Subject> getSubject(@RequestParam Long subject_id) {
        return subjectService.getBySubjectId(subject_id);
    }

    @PostMapping("/save_subject")
    public ResponseEntity<String> saveSubject(@RequestBody Subject subject) {
        boolean isSaved = subjectService.saveSubject(subject);
        if (isSaved) {
            return ResponseEntity.ok("Предмет добавлен!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Предмет не сохранен!");
        }
    }

    @PutMapping("/update_subject")
    public ResponseEntity<String> updateSubject(@RequestBody Subject subject) {
        boolean isUpdated = subjectService.updateSubject(subject);
        if (isUpdated) {
            return ResponseEntity.ok("Предмет обновлен!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Предмет не обновлен!");
        }
    }

    @DeleteMapping("/delete_subject/{subject_id}")
    public ResponseEntity<String> deleteSubject(@PathVariable Long subject_id) {
        boolean isDeleted = subjectService.deleteSubject(subject_id);
        if (isDeleted) {
            return ResponseEntity.ok("Предмет удалён!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Предмет не удален!");
        }
    }
}
