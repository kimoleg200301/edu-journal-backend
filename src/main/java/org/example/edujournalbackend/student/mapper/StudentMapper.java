package org.example.edujournalbackend.student.mapper;

import org.example.edujournalbackend.student.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class StudentMapper {
    public static Student mapStudent(ResultSet rs) throws SQLException {
        return new Student(
                rs.getLong("student_id"),
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getDate("birth_date").toLocalDate(),
                rs.getString("gender"),
                rs.getString("iin"),
                rs.getString("living_adress"),
                rs.wasNull() ? Optional.empty() : Optional.of(rs.getLong("edu_group_id"))
        );
    }
}
