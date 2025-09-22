package org.example.edujournalbackend.group;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Группы", description = "Операции для управления учебными группами")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/info_group")
    @Operation(summary = "Информация о группе", description = "Возвращает информацию о группе по ID")
    public Optional<Group> getGroup(
            @Parameter(description = "ID группы", required = true)
            @RequestParam Long edu_group_id) {
        return groupService.findByGroupId(edu_group_id);
    }
    @GetMapping("/")
    @Operation(summary = "Список групп", description = "Возвращает все учебные группы")
    public List<Group> getAllGroup() {
        return groupService.findAllGroups();
    }
    @PostMapping("/save_group")
    @Operation(summary = "Создать группу", description = "Создает новую учебную группу")
    public ResponseEntity<String> saveGroup(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные группы", required = true)
            @RequestBody Group group) {
        boolean isSaved = groupService.saveGroup(group);
        if (isSaved) {
            return ResponseEntity.ok("Группа создана!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Группа не создана!");
        }
    }
    @PutMapping("/update_group")
    @Operation(summary = "Обновить группу", description = "Обновляет данные учебной группы")
    public ResponseEntity<String> updateGroup(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Обновленные данные группы", required = true)
            @RequestBody Group group) {
        boolean isUpdated = groupService.updateGroup(group);
        if (isUpdated) {
            return ResponseEntity.ok("Группа обновлена!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Группа не обновлена!");
        }
    }
    @DeleteMapping("/delete_group/{edu_group_id}")
    @Operation(summary = "Удалить группу", description = "Удаляет учебную группу по ID")
    public ResponseEntity<String> deleteGroup(
            @Parameter(description = "ID группы", required = true)
            @PathVariable Long edu_group_id) {
        boolean isDeleted = groupService.deleteGroup(edu_group_id);
        if (isDeleted) {
            return ResponseEntity.ok("Группа удалена!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Группа не удалена!");
        }
    }
    @GetMapping("/unadded_students_by_groups")
    @Operation(summary = "Студенты без группы", description = "Возвращает студентов, не добавленных в группы")
    public List<Student> findAllUnaddedStudentsByGroups() {
        return groupService.findAllUnaddedStudentsByGroups();
    }
    @PutMapping("/add_unadded_students_in_group")
    @Operation(summary = "Добавить студентов в группу", description = "Добавляет список студентов в группу")
    public ResponseEntity<String> addUnaddedStudentsInGroup(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Список студентов", required = true)
            @RequestBody List<Student> students,
            @Parameter(description = "ID группы", required = true)
            @RequestParam Long edu_group_id) {
        boolean isAddedUnaddedStudents = groupService.addUnaddedStudentsInGroup(students, edu_group_id);
        if (isAddedUnaddedStudents) {
            return ResponseEntity.ok("Студенты добавлены в группу!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Студенты не добавлены в группу!");
        }
    }
    @GetMapping("/added_students_by_groups")
    @Operation(summary = "Студенты по группам", description = "Возвращает студентов, добавленных по всем группам")
    public List<Student> findAllAddedStudentsByGroups() {
        return groupService.findAllAddedStudentsByGroups();
    }
    @GetMapping("/added_students_by_group_id")
    @Operation(summary = "Студенты группы", description = "Возвращает студентов конкретной группы по ID")
    public List<Student> findAddedStudentsByGroupId(
            @Parameter(description = "ID группы", required = true)
            @RequestParam Long edu_group_id) {
        return groupService.findAddedStudentsByGroupId(edu_group_id);
    }
    @DeleteMapping("/delete_student_from_group")
    @Operation(summary = "Удалить студента из группы", description = "Удаляет студента из его группы по ID студента")
    public ResponseEntity<String> deleteStudentFromGroup(
            @Parameter(description = "ID студента", required = true)
            @RequestParam Long student_id) {
        boolean isDeletedStudentFromGroup = groupService.deleteStudentFromGroup(student_id);
        if (isDeletedStudentFromGroup) {
            return ResponseEntity.ok("Студент удален из группы!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Студент не удален из группы!");
        }
    }
    @GetMapping("/unadded_subjects_by_group_id")
    @Operation(summary = "Предметы не в группе", description = "Возвращает предметы, не добавленные в группу")
    public List<Subject> findUnaddedSubjectsInGroups(
            @Parameter(description = "ID группы", required = true)
            @RequestParam Long edu_group_id) {
        return groupService.findUnaddedSubjectsInGroups(edu_group_id);
    }
    @PutMapping("/add_subjects_in_group")
    @Operation(summary = "Добавить предметы в группу", description = "Добавляет список предметов в группу")
    public ResponseEntity<Map<String, String>> addSubjectsInGroup(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Список предметов", required = true)
            @RequestBody List<Subject> subjects,
            @Parameter(description = "ID группы", required = true)
            @RequestParam Long edu_group_id) {
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
    @Operation(summary = "Предметы группы", description = "Возвращает предметы, добавленные в группу")
    public List<Subject> findAddedSubjectsInGroup(
            @Parameter(description = "ID группы", required = true)
            @RequestParam Long edu_group_id) {
        return groupService.findAddedSubjectsInGroup(edu_group_id);
    }
    @DeleteMapping("/delete_subject_from_group")
    @Operation(summary = "Удалить предмет из группы", description = "Удаляет предмет из группы по ID группы и предмета")
    public ResponseEntity<String> deleteAddedSubjectFromGroup(
            @Parameter(description = "ID группы", required = true)
            @RequestParam Long edu_group_id,
            @Parameter(description = "ID предмета", required = true)
            @RequestParam Long subject_id) {
        boolean isDeletedSubjectsFromGroup = groupService.deleteAddedSubjectFromGroup(edu_group_id, subject_id);
        if (isDeletedSubjectsFromGroup) {
            return ResponseEntity.ok("Предмет был удален из группы!");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Предмет не был удален из группы!");
        }
    }
}
