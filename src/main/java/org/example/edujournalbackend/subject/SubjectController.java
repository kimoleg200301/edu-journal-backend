package org.example.edujournalbackend.subject;

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
public class SubjectController {
    private final SubjectService subjectService;

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
    public ResponseEntity<Map<String, String>> saveSubject(@RequestBody Subject subject) {
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
    public ResponseEntity<Map<String, String>> updateSubject(@RequestBody Subject subject) {
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
    public ResponseEntity<Map<String, String>> deleteSubject(@PathVariable Long subject_id) {
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
