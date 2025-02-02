package org.example.edujournalbackend.group;

import org.example.edujournalbackend.subject.Subject;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupDataAccessService implements GroupDao {
    private final DataSource dataSource;

    public GroupDataAccessService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Group findByGroupId(Long edu_group_id) {
        String sql = "select * from edu_groups where edu_group_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, edu_group_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Group(
                        rs.getLong("edu_group_id"),
                        rs.getString("name"),
                        rs.getDate("created").toLocalDate()
                );
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
}
