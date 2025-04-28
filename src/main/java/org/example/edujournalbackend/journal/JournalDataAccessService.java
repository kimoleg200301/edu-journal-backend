package org.example.edujournalbackend.journal;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
                SELECT
                  s.student_id,
                  s.firstname,
                  s.lastname,
                  g.name AS group_name,
                  subj.name AS subject_name,
                  j.mark,
                  j.date_for
                FROM students s
                INNER JOIN edu_groups g ON s.edu_group_id = g.edu_group_id
                INNER JOIN list_of_subjects ls ON g.edu_group_id = ls.edu_group_id
                INNER JOIN subjects subj ON ls.subject_id = subj.subject_id
                LEFT JOIN journals j ON ls.list_of_subject_id = j.list_of_subject_id
                  AND s.student_id = j.student_id
                  AND j.date_for BETWEEN CONCAT(?, '-01') AND LAST_DAY(CONCAT(?, '-01'))
                WHERE g.edu_group_id = ?  -- ID группы
                  AND subj.subject_id = ?;  -- ID предмета
                """;
//        String sql = """
//                SELECT
//                    s.student_id,
//                    s.firstname,
//                    s.lastname,
//                    g.name AS group_name,
//                    subj.name AS subject_name,
//                    j.mark,
//                    j.date_for
//                FROM students s
//                inner JOIN edu_groups g ON s.edu_group_id = g.edu_group_id
//                inner JOIN list_of_subjects ls ON g.edu_group_id = ls.edu_group_id
//                inner JOIN subjects subj ON ls.subject_id = subj.subject_id
//                left JOIN journals j ON ls.list_of_subject_id = j.list_of_subject_id AND s.student_id = j.student_id
//                WHERE g.edu_group_id = ?  -- ID группы
//                  AND subj.subject_id = ?  -- ID предмета
//                ORDER BY j.date_for DESC;
//                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, date);
            stmt.setString(2, date);
            stmt.setLong(3, edu_group_id);
            stmt.setLong(4, subject_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LocalDate date_ = rs.getDate("date_for") != null ? rs.getDate("date_for").toLocalDate() : null; // Проверка на NULL
                journal.add(new Journal(
                        rs.getLong("student_id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getObject("mark", Integer.class), // Может вернуть null, если mark = NULL
                        date_,
                        date_ != null ? date_.getYear() : 0,
                        date_ != null ? date_.getMonthValue() : 0,
                        date_ != null ? date_.getDayOfMonth() : 0
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
