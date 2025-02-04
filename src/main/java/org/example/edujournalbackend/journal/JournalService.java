package org.example.edujournalbackend.journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {
    private final JournalDao journalDao;

    @Autowired
    public JournalService(JournalDao journalDao) {
        this.journalDao = journalDao;
    }
    public Boolean setMarks (List<Journal> journals) {
        return journalDao.setMarks(journals);
    }
    public Boolean deleteMarks (List<Journal> journals) {
        return journalDao.deleteMarks(journals);
    }
}
