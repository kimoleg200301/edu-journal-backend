package org.example.edujournalbackend.group;

import org.example.edujournalbackend.student.Student;
import org.example.edujournalbackend.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupDao groupDao;

    @Autowired
    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }
    public Optional<Group> findByGroupId(Long edu_group_id) {
        return groupDao.findByGroupId(edu_group_id);
    }
    public List<Group> findAllGroups() {
        return groupDao.findAllGroups();
    }
    public Boolean saveGroup(Group group) {
        return groupDao.save(group);
    }
    public Boolean updateGroup(Group group) {
        return groupDao.update(group);
    }
    public Boolean deleteGroup(Long edu_group_id) {
        return groupDao.delete(edu_group_id);
    }
    public List<Student> findAllUnaddedStudentsByGroups() {
        return groupDao.findAllUnaddedStudentsByGroups();
    }
    public Boolean addUnaddedStudentsInGroup(List<Student> students, Long edu_group_id) {
        return groupDao.addUnaddedStudentsInGroup(students, edu_group_id);
    }
    public Boolean deleteStudentFromGroup(Long student_id) {
        return groupDao.deleteStudentFromGroup(student_id);
    }
    public Boolean addSubjectsInGroup(List<Subject> subject, Long edu_group_id) {
        return groupDao.addSubjectsInGroup(subject, edu_group_id);
    }
}
