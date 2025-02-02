package org.example.edujournalbackend.student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    Optional<Student> findById(Long id);
    List<Student> findAll();
    Boolean save(Student student);
    Boolean update(Student student);
    Boolean delete(Long student_id);
}
