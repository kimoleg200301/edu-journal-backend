package org.example.edujournalbackend.journal;

import java.util.List;

public interface JournalDao {
    Boolean setMarks (List<Journal> journals, Long edu_group_id, Long subject_id); // 1
    Boolean deleteMarks (List<Journal> journals, Long edu_group_id, Long subject_id);

    // 1. сначала получить ид группы, и по группе найти предмет, который выбрал пользователь (по параметрам).
    //    Далее благодаря двум параметрам найти один ид списка предметов (list_of_subjects) и в журнале (journal) в json указать список студента и оценку.
    //    Обновлять оценки с условием, если уже найден студент

    //    добавить реализацию выставления оценок по датам
}
