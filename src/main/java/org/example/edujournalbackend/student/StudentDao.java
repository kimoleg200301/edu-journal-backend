package org.example.edujournalbackend.student;

import java.util.List;

public interface StudentDao {
    Student findById(Long id);
    List<Student> findAll();
    Boolean save(Student student);
    Boolean update(Student student);
    Boolean delete(Long student_id);
}
