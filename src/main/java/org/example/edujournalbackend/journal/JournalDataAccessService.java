package org.example.edujournalbackend.journal;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

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
    public Boolean setMarks (List<Journal> journals, Long edu_group_id, Long subject_id) {
        String sql = """
            INSERT INTO journals (list_of_subject_id, student_id, mark) 
            VALUES ((select list_of_subject_id from list_of_subjects where edu_group_id = ? and subject_id = ?), ?, ?) 
            ON DUPLICATE KEY UPDATE mark = VALUES(mark)
        """; // запрос, который инсертит значения, но если уже имеется первичные ключи student_id и list_of_subjects_id (при выполненном запросе unique index этих полей), то обновляет mark, иначе добавит новую запись

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Journal journal: journals) {
                stmt.setLong(1, edu_group_id);
                stmt.setLong(2, subject_id);
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
    public Boolean deleteMarks (List<Journal> journals, Long edu_group_id, Long subject_id) {
        String sql = "DELETE FROM journals WHERE list_of_subject_id = (select list_of_subject_id from list_of_subjects where edu_group_id = ? AND subject_id = ?) and student_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Journal journal: journals) {
                stmt.setLong(1, edu_group_id);
                stmt.setLong(2, subject_id);
                stmt.setLong(3, journal.getStudent_id());

                stmt.addBatch();
            }
            int [] deletedMarks = stmt.executeBatch();
            return deletedMarks[0] > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Запрос не корректен!", e);
        }
    }
}
