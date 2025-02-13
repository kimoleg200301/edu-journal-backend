package org.example.edujournalbackend.journal;

import org.example.edujournalbackend.group.Group;
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
public class JournalDataAccessService implements JournalDao {
    private final DataSource dataSource;

    public JournalDataAccessService(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<Journal> findJournalByMonth(Long edu_group_id, Long subject_id, String date) {
        List<Journal> journal = new ArrayList<>();
        String sql = """
            select s.firstname,
                s.lastname,
                j.mark,
                los.subject_id,
                j.date_for from students s
            left join journals j on s.student_id = j.student_id
            inner join list_of_subjects los on j.list_of_subject_id = los.list_of_subject_id
            where los.list_of_subject_id = (select list_of_subject_id from list_of_subjects where edu_group_id = ? and subject_id = ?) and j.date_for between CONCAT(?, '-01') and LAST_DAY(CONCAT(?, '-01'));
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, edu_group_id);
            stmt.setLong(2, subject_id);
            stmt.setString(3, date);
            stmt.setString(4, date);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                journal.add(new Journal(
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getInt("mark"),
                        rs.getDate("date_for").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка в запросе!", e);
        }
        return journal;
    }
    @Override
    public Boolean setMarks(List<Journal> journals, Long edu_group_id, Long subject_id) {
        String sql = """
            INSERT INTO journals (list_of_subject_id, student_id, mark, date_for) 
            VALUES ((select list_of_subject_id from list_of_subjects where edu_group_id = ? and subject_id = ?), ?, ?, ?) 
            ON DUPLICATE KEY UPDATE mark = VALUES(mark)
        """; // запрос, который инсертит значения, но если уже имеется первичные ключи student_id и list_of_subjects_id (при выполненном запросе unique index этих полей), то обновляет mark, иначе добавит новую запись

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (Journal journal: journals) {
                stmt.setLong(1, edu_group_id);
                stmt.setLong(2, subject_id);
                stmt.setLong(3, journal.getStudent_id());
                stmt.setInt(4, journal.getMark());
                stmt.setDate(5, java.sql.Date.valueOf(journal.getDate_for()));

                stmt.addBatch();
            }
            int[] updatedMarks = stmt.executeBatch();
            return updatedMarks[0] > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Запрос не корректен!", e);
        }
    }
    @Override
    public Boolean deleteMarks(List<Journal> journals, Long edu_group_id, Long subject_id) {
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
