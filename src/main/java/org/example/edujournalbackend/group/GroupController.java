package org.example.edujournalbackend.group;

import org.example.edujournalbackend.student.Student;
import org.example.edujournalbackend.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/info_group")
    public Optional<Group> getGroup(@RequestParam Long edu_group_id) {
        return groupService.findByGroupId(edu_group_id);
    }
    @GetMapping("/")
    public List<Group> getAllGroup() {
        return groupService.findAllGroups();
    }
    @PostMapping("/save_group")
    public ResponseEntity<String> saveGroup(@RequestBody Group group) {
        boolean isSaved = groupService.saveGroup(group);
        if (isSaved) {
            return ResponseEntity.ok("Группа создана!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Группа не создана!");
        }
    }
    @PutMapping("/update_group")
    public ResponseEntity<String> updateGroup(@RequestBody Group group) {
        boolean isUpdated = groupService.updateGroup(group);
        if (isUpdated) {
            return ResponseEntity.ok("Группа обновлена!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Группа не обновлена!");
        }
    }
    @DeleteMapping("/delete_group/{edu_group_id}")
    public ResponseEntity<String> deleteGroup(@PathVariable Long edu_group_id) {
        boolean isDeleted = groupService.deleteGroup(edu_group_id);
        if (isDeleted) {
            return ResponseEntity.ok("Группа удалена!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Группа не удалена!");
        }
    }
    @GetMapping("/unadded_students_by_groups")
    public List<Student> findAllUnaddedStudentsByGroups() {
        return groupService.findAllUnaddedStudentsByGroups();
    }
    @PutMapping("/add_unadded_students_in_group")
    public ResponseEntity<String> addUnaddedStudentsInGroup(@RequestBody List<Student> students, @RequestParam Long edu_group_id) {
        boolean isAddedUnaddedStudents = groupService.addUnaddedStudentsInGroup(students, edu_group_id);
        if (isAddedUnaddedStudents) {
            return ResponseEntity.ok("Студенты добавлены в группу!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Студенты не добавлены в группу!");
        }
    }
    @GetMapping("/added_students_by_groups")
    public List<Student> findAllAddedStudentsByGroups() {
        return groupService.findAllAddedStudentsByGroups();
    }
    @GetMapping("/added_students_by_group_id")
    public List<Student> findAddedStudentsByGroupId(@RequestParam Long edu_group_id) {
        return groupService.findAddedStudentsByGroupId(edu_group_id);
    }
    @DeleteMapping("/delete_student_from_group")
    public ResponseEntity<String> deleteStudentFromGroup(@RequestParam Long student_id) {
        boolean isDeletedStudentFromGroup = groupService.deleteStudentFromGroup(student_id);
        if (isDeletedStudentFromGroup) {
            return ResponseEntity.ok("Студент удален из группы!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Студент не удален из группы!");
        }
    }
    @GetMapping("/unadded_subjects_by_group_id")
    public List<Subject> findUnaddedSubjectsInGroups(@RequestParam Long edu_group_id) {
        return groupService.findUnaddedSubjectsInGroups(edu_group_id);
    }
    @PutMapping("/add_subjects_in_group")
    public ResponseEntity<Map<String, String>> addSubjectsInGroup(@RequestBody List<Subject> subjects, @RequestParam Long edu_group_id) {
        boolean isAddedSubjectsInGroup = groupService.addSubjectsInGroup(subjects, edu_group_id);

        Map<String, String> response = new HashMap<>();

        if (isAddedSubjectsInGroup) {
            response.put("message", "Предметы были добавлены в группу!");
            return ResponseEntity.ok(response);
        }
        else {
            response.put("error", "Предметы не были добавлены в группу!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping("/added_subjects_by_group_id")
    public List<Subject> findAddedSubjectsInGroup(@RequestParam Long edu_group_id) {
        return groupService.findAddedSubjectsInGroup(edu_group_id);
    }
    @DeleteMapping("/delete_subject_from_group")
    public ResponseEntity<String> deleteAddedSubjectFromGroup(@RequestParam Long edu_group_id, @RequestParam Long subject_id) {
        boolean isDeletedSubjectsFromGroup = groupService.deleteAddedSubjectFromGroup(edu_group_id, subject_id);
        if (isDeletedSubjectsFromGroup) {
            return ResponseEntity.ok("Предмет был удален из группы!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Предмет не был удален из группы!");
        }
    }
}
