package org.example.edujournalbackend.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    @GetMapping("/info_group")
    public Group getGroup(@RequestParam Long edu_group_id) {
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
}
