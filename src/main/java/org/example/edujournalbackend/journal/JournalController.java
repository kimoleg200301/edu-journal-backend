package org.example.edujournalbackend.journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.ResponseCache;
import java.util.List;

@RestController
@RequestMapping("/api/v1/journals")
public class JournalController {
    private final JournalService journalService;

    @Autowired
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }
    @PostMapping("/set_marks")
    public ResponseEntity<String> setMarks(@RequestBody List<Journal> journals, @RequestParam Long edu_group_id, @RequestParam Long subject_id) {
        boolean isUpdatedMarks = journalService.setMarks(journals, edu_group_id, subject_id);
        if (isUpdatedMarks) {
            return ResponseEntity.ok("Оценки выставлены/обновлены!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Оценки не выставлены/обновлены!");
        }
    }
    @DeleteMapping("/delete_marks")
    public ResponseEntity<String> deleteMarks(@RequestBody List<Journal> journals, @RequestParam Long edu_group_id, @RequestParam Long subject_id) {
        boolean isDeletedMarks = journalService.deleteMarks(journals, edu_group_id, subject_id);
        if (isDeletedMarks) {
            return ResponseEntity.ok("Оценки удалены!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Оценки не удалены!");
        }
    }
}
