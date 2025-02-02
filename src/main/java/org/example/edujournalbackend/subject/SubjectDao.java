package org.example.edujournalbackend.subject;

import org.example.edujournalbackend.student.Student;

import java.util.List;

public interface SubjectDao {
    Subject findBySubjectId(Long subject_id);
    List<Subject> findAllSubjects();
    Boolean save(Subject subject);
    Boolean update(Subject subject);
    Boolean delete(Long subject_id);
}
