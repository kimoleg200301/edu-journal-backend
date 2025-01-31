package org.example.edujournalbackend.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDataAccessService implements StudentDao {
    @Autowired
    private DataSource dataSource;

    @Override
    public Student findById(Long student_id) {
        String sql = "select * from students where student_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, student_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String sql = "select * from students";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getLong("student_id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birth_date").toLocalDate(),
                        rs.getString("gender"),
                        rs.getString("iin"),
                        rs.getString("living_adress"),
                        rs.wasNull() ? Optional.empty() : Optional.of(rs.getLong("edu_group_id"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(Student student) {

    }
}
