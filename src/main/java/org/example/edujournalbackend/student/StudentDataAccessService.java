package org.example.edujournalbackend.student;

import org.example.edujournalbackend.student.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.SQL;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDataAccessService implements StudentDao {
    private final DataSource dataSource;

    @Autowired
    public StudentDataAccessService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Student> findById(Long student_id) {
        String sql = "select * from students where student_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, student_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(StudentMapper.mapStudent(rs));
            }
            else {
                throw new RuntimeException("Студент не найден!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске студента!", e);
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String sql = "select * from students";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                students.add(StudentMapper.mapStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    @Override
    public Boolean save(Student student) {
        String sql = "insert into students (firstname, lastname, birth_date, gender, IIN, living_adress) values (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,student.getFirstname());
            stmt.setString(2, student.getLastname());
            stmt.setDate(3, java.sql.Date.valueOf(student.getBirth_date()));
            stmt.setString(4, student.getGender());
            stmt.setString(5, student.getIin());
            stmt.setString(6, student.getLiving_adress());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при сохранении студента!", e);
        }
    }
    @Override
    public Boolean update(Student student) {
        String sql = "update students set firstname = ?, lastname = ?, birth_date = ?, gender = ?, IIN = ?, living_adress = ? where student_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,student.getFirstname());
            stmt.setString(2, student.getLastname());
            stmt.setDate(3, java.sql.Date.valueOf(student.getBirth_date()));
            stmt.setString(4, student.getGender());
            stmt.setString(5, student.getIin());
            stmt.setString(6, student.getLiving_adress());
            stmt.setLong(7, student.getStudent_id());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении студента!", e);
        }
    }

    @Override
    public Boolean delete(Long student_id) {
        String sql = "delete from students where student_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, student_id);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении студента вероятно в запросе", e);
        }
    }
}
