package org.example.edujournalbackend.journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.ResponseCache;
import java.util.List;

@RestController
@RequestMapping("/v1/journals")
public class JournalController {
    private final JournalService journalService;

    @Autowired
    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping("/set_marks")
    public ResponseEntity<String> setMarks(List<Journal> journals) {
        boolean isUpdatedMarks = journalService.setMarks(journals);
        if (isUpdatedMarks) {
            return ResponseEntity.ok("Оценки выставлены/обновлены!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Оценки не выставлены/обновлены!");
        }
    }
    @DeleteMapping("/delete_marks")
    public ResponseEntity<String> deleteMarks(List<Journal> journals) {
        boolean isDeletedMarks = journalService.deleteMarks(journals);
        if (isDeletedMarks) {
            return ResponseEntity.ok("Оценки удалены!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Оценки не удалены!");
        }
    }
}
