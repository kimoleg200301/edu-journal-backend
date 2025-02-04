package org.example.edujournalbackend.group;

import java.util.List;
import java.util.Optional;

import org.example.edujournalbackend.student.Student;
import org.example.edujournalbackend.subject.Subject;

public interface GroupDao {
    Optional<Group> findByGroupId(Long edu_group_id);
    List<Group> findAllGroups();
    Boolean save(Group group);
    Boolean update(Group group);
    Boolean delete(Long edu_group_id);
    List<Student> findAllUnaddedStudentsByGroups(); // 1
    Boolean addUnaddedStudentsInGroup(List<Student> students, Long edu_group_id); // 1.1
    List<Student> findAllAddedStudentsByGroups(); // 1.1.1
    List<Student> findAddedStudentsByGroupId(Long edu_group_id); // 1.1.2
    Boolean deleteStudentFromGroup(Long edu_group_id); // 2
    Boolean addSubjectsInGroup(List<Subject> subject, Long edu_group_id); // 3
    List<Subject> findAddedSubjectsInGroup(Long edu_group_id); // 4
    Boolean deleteAddedSubjectFromGroup(Long edu_group_id, Long subject_id); // 5

    // 1.     отобразить список не добавленных студентов в группы
    // 1.1.   добавить не добавленных студентов в группы
    // 1.1.1. отобразить всех добавленных студентов в группы
    // 1.1.2. отобразить добавленных студентов в выбранную группу
    // 2.     удаление добавленных студентов из выбранной группы
    // 3.     добавить функционал добавления предметов в выбранную группу
    // 4.     отобразить список добавленных предметов в выбранную группу
    // 5.     удалить добавленных предметов в выбранную группу
}
