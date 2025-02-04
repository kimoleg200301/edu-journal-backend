package org.example.edujournalbackend.journal;

public class Journal {
    public Long journal_id;
    public Long list_of_subjects_id;
    public Long edu_group_id;
    public Long student_id;
    public Integer mark;

    public Journal() {}

    public Journal(Long journal_id, Long list_of_subjects_id, Long edu_group_id, Long student_id, Integer mark) {
        this.journal_id = journal_id;
        this.list_of_subjects_id = list_of_subjects_id;
        this.edu_group_id = edu_group_id;
        this.student_id = student_id;
        this.mark = mark;
    }

    public Journal(Long list_of_subjects_id, Long edu_group_id, Long student_id, Integer mark) {
        this.list_of_subjects_id = list_of_subjects_id;
        this.edu_group_id = edu_group_id;
        this.student_id = student_id;
        this.mark = mark;
    }

    public Long getJournal_id() {
        return journal_id;
    }

    public void setJournal_id(Long journal_id) {
        this.journal_id = journal_id;
    }

    public Long getList_of_subjects_id() {
        return list_of_subjects_id;
    }

    public void setList_of_subjects(Long list_of_subjects_id) {
        this.list_of_subjects_id = list_of_subjects_id;
    }

    public Long getEdu_group_id() {
        return edu_group_id;
    }

    public void setEdu_group_id(Long edu_group_id) {
        this.edu_group_id = edu_group_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "journal_id=" + journal_id +
                ", list_of_subjects_id=" + list_of_subjects_id +
                ", edu_group_id=" + edu_group_id +
                ", student_id=" + student_id +
                ", mark=" + mark +
                '}';
    }
}
