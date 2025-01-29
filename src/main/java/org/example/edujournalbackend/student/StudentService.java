package org.example.edujournalbackend.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {
    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "Oleg",
                        "Kim",
                        LocalDate.of(2003, Month.NOVEMBER, 14),
                        "Male",
                        "031114000079",
                        "Bogenbay batyr"
                )
        );
    }
}
