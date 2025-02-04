package org.example.edujournalbackend.journal;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JournalDataAccessService implements JournalDao {
    private final DataSource dataSource;

    public JournalDataAccessService(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public Boolean setMarks (List<Journal> journals) {
        String sql = """
            INSERT INTO journals (list_of_subjects_id, student_id, edu_group_id, mark) 
            VALUES (?, ?, (SELECT edu_group_id FROM students WHERE student_id = ?), ?) 
            ON DUPLICATE KEY UPDATE mark = VALUES(mark)
        """; // запрос, который инсертит значения, но если уже имеется первичные ключи student_id и list_of_subjects_id, то обновляет mark

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Journal journal: journals) {
                stmt.setLong(1, journal.getList_of_subjects_id());
                stmt.setLong(2, journal.getStudent_id());
                stmt.setLong(3, journal.getStudent_id());
                stmt.setInt(4, journal.getMark());

                stmt.addBatch();
            }
            int[] updatedMarks = stmt.executeBatch();
            return updatedMarks[0] > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Запрос не корректен!", e);
        }
    }
    @Override
    public Boolean deleteMarks (List<Journal> journals) {
        String sql = "DELETE FROM journals WHERE student_id = ? AND list_of_subjects_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Journal journal: journals) {
                stmt.setLong(1, journal.getStudent_id());
                stmt.setLong(2, journal.getList_of_subjects_id());

                stmt.addBatch();
            }
            int [] deletedMarks = stmt.executeBatch();
            return deletedMarks[0] > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Запрос не корректен!", e);
        }
    }
}
