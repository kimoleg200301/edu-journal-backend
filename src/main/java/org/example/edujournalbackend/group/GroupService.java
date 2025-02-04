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
    public List<Student> findAllAddedStudentsByGroups() {
        return groupDao.findAllAddedStudentsByGroups();
    }
    public List<Student> findAddedStudentsByGroupId(Long edu_group_id) {
        return groupDao.findAddedStudentsByGroupId(edu_group_id);
    }
    public Boolean deleteStudentFromGroup(Long student_id) {
        return groupDao.deleteStudentFromGroup(student_id);
    }
    public Boolean addSubjectsInGroup(List<Subject> subjects, Long edu_group_id) {
        return groupDao.addSubjectsInGroup(subjects, edu_group_id);
    }
    public List<Subject> findAddedSubjectsInGroup(Long edu_group_id) {
        return groupDao.findAddedSubjectsInGroup(edu_group_id);
    }
    public Boolean deleteAddedSubjectFromGroup(Long edu_group_id, Long subject_id) {
        return groupDao.deleteAddedSubjectFromGroup(edu_group_id, subject_id);
    }
}
