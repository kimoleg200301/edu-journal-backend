package org.example.edujournalbackend.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.SQL;
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
public class SubjectDataAccessService implements SubjectDao {
    private final DataSource dataSource;

    @Autowired
    public SubjectDataAccessService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Subject> findBySubjectId(Long subject_id) {
        String sql = "select * from subjects where subject_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, subject_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Subject(
                        rs.getLong("subject_id"),
                        rs.getString("name"),
                        rs.getString("subject_code"),
                        rs.getInt("credits")
                ));
            }
            else {
                throw new RuntimeException("Предмет не найден!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске предмета!", e);
        }
    }
    public List<Subject> findAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        String sql = "select * from subjects";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                subjects.add(new Subject(
                        rs.getLong("subject_id"),
                        rs.getString("name"),
                        rs.getString("subject_code"),
                        rs.getInt("credits")
                ));
            }
            return subjects;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске предмета!", e);
        }
    }
    @Override
    public Boolean save(Subject subject) {
        String sql = "insert into subjects (name, subject_code, credits) values (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, subject.getName());
            stmt.setString(2, subject.getSubject_code());
            stmt.setInt(3, subject.getCredits());

            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при добавлении предмета!", e);
        }
    }
    @Override
    public Boolean update(Subject subject) {
        String sql = "update subjects set name = ?, subject_code = ?, credits = ? where subject_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, subject.getName());
            stmt.setString(2, subject.getSubject_code());
            stmt.setInt(3, subject.getCredits());
            stmt.setLong(4, subject.getSubject_id());

            int rowsUpdated = stmt.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении предмета!", e);
        }
    }

    @Override
    public Boolean delete(Long subject_id) {
        String sql = "delete from subjects where subject_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, subject_id);

            int rowsDeleted = stmt.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении предмета!", e);
        }
    }
}
