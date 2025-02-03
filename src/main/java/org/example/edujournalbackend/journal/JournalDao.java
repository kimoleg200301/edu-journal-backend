package org.example.edujournalbackend.journal;

import java.util.List;

public interface JournalDao {
    List<Journal> updateJournal ();

    // при создании записи в таблицу list_of_subjects взять ИД edu_group_id и в таблицу journal внести ИД list_of_subjects_id
    // при добавлении студента в группу добавлять в таблицу journal
    // 1. на странице выставления оценок будут отображены список обучающихся.
    //    Будут ИД group_id, subject_id, student_id выбранных.
    //    При добавлении/изменении/удалении оценки в журнале
}
