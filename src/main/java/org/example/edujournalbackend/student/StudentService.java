package org.example.edujournalbackend.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public Optional<Student> getStudentById(Long student_id) {
        return studentDao.findById(student_id);
    }
    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }
    public boolean saveStudent(Student student) {
        return studentDao.save(student);
    }
    public boolean updateStudent(Student student) {
        return studentDao.update(student);
    }
    public boolean deleteStudent(Long student_id) {
        return studentDao.delete(student_id);
    }
}
