package org.example.edujournalbackend.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {
    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    public Student getStudentById(Long id) {
        return studentDao.findById(id);
    }
    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }
//    public Student updateStudent(Student student) {
//        return studentDao.update(student);
//    }
}
