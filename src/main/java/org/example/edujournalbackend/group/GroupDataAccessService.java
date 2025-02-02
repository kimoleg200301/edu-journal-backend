package org.example.edujournalbackend.group;

import org.example.edujournalbackend.student.Student;
import org.example.edujournalbackend.student.mapper.StudentMapper;
import org.example.edujournalbackend.subject.Subject;
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
public class GroupDataAccessService implements GroupDao {
    private final DataSource dataSource;

    public GroupDataAccessService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Group> findByGroupId(Long edu_group_id) {
        String sql = "select * from edu_groups where edu_group_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, edu_group_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(new Group(
                        rs.getLong("edu_group_id"),
                        rs.getString("name"),
                        rs.getDate("created").toLocalDate()
                ));
            }
            else {
                throw new RuntimeException("Предмет не найден!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка в запросе!", e);
        }
    }
    @Override
    public List<Group> findAllGroups() {
        List<Group> groups = new ArrayList<>();
        String sql = "select * from edu_groups";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                groups.add(new Group(
                        rs.getLong("edu_group_id"),
                        rs.getString("name"),
                        rs.getDate("created").toLocalDate()
                ));
            }
            return groups;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка в запросе!", e);
        }
    }
    @Override
    public Boolean save(Group group) {
        String sql = "insert into edu_groups (name) values (?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, group.getName());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка в запросе!", e);
        }
    }
    @Override
    public Boolean update(Group group) {
        String sql = "update edu_groups set name = ? where edu_group_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, group.getName());
            stmt.setLong(2, group.getEdu_group_id());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка в запросе!", e);
        }
    }
    @Override
    public Boolean delete(Long edu_group_id) {
        String sql = "delete from edu_groups where edu_group_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, edu_group_id);

            int rowsDelete = stmt.executeUpdate();
            return rowsDelete > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка в запросе!", e);
        }
    }
    @Override
    public List<Student> findAllUnaddedStudentsByGroups() {
        List<Student> unaddedStudents = new ArrayList<>();
        String sql = "select * from students where edu_group_id is null";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                unaddedStudents.add(StudentMapper.mapStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unaddedStudents;
    }
    @Override
    public Boolean addUnaddedStudentsInGroup(List<Student> students, Long edu_group_id) {
        String sql = "update students set edu_group_id = ? where student_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Student student: students) {
                stmt.setLong(1, edu_group_id);
                stmt.setObject(2, student.getStudent_id());
                stmt.addBatch();
            }
            int[] unaddedStudentsIsUpdated = stmt.executeBatch();
            return unaddedStudentsIsUpdated[0] > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при добавлении/изменении студента в группу", e);
        }
    }
    @Override
    public Boolean deleteStudentFromGroup(Long student_id) {
        String sql = "update students set edu_group_id = null where student_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, student_id);

            int studentIsDeleted = stmt.executeUpdate();
            return studentIsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении студента из группы", e);
        }
    }
    @Override
    public Boolean addSubjectsInGroup(List<Subject> subjects, Long edu_group_id) {
        String sql = "insert into list_of_subjects (edu_group_id, subject_id) values (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Subject subject: subjects) {
                stmt.setLong(1, edu_group_id);
                stmt.setLong(2, subject.getSubject_id());
                stmt.addBatch();
            }
            int[] subjectsIsAdded = stmt.executeBatch();
            return subjectsIsAdded[0] > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при добавлении предметов в группу", e);
        }

    }
}
