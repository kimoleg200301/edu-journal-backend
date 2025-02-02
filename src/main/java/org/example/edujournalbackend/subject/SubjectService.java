package org.example.edujournalbackend.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectDao subjectDao;

    @Autowired
    public SubjectService (SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    public List<Subject> getAllSubjects() {
        return subjectDao.findAllSubjects();
    }

    public Subject getBySubjectId(Long subject_id) {
        return subjectDao.findBySubjectId(subject_id);
    }

    public boolean saveSubject(Subject subject) {
        return subjectDao.save(subject);
    }

    public boolean updateSubject(Subject subject) {
        return subjectDao.update(subject);
    }

    public boolean deleteSubject(Long subject_id) {
        return subjectDao.delete(subject_id);
    }
}
