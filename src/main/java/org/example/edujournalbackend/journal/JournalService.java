package org.example.edujournalbackend.journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {
    private final JournalDao journalDao;

    public JournalService(JournalDao journalDao) {
        this.journalDao = journalDao;
    }

    public List<Journal> findJournalByMonth (Long edu_group_id, Long subject_id, String date) {
        return journalDao.findJournalByMonth(edu_group_id, subject_id, date);
    }
    public Boolean setMarks (List<Journal> journals, Long edu_group_id, Long subject_id) {
        return journalDao.setMarks(journals, edu_group_id, subject_id);
    }
    public Boolean deleteMarks (List<Journal> journals, Long edu_group_id, Long subject_id) {
        return journalDao.deleteMarks(journals, edu_group_id, subject_id);
    }
}
