package org.example.edujournalbackend.journal;

public class Journal {
    public Long journal_id;
    public Long list_of_subjects;
    public Integer mark;

    public Journal() {}

    public Journal(Long journal_id, Long list_of_subjects, Integer mark) {
        this.journal_id = journal_id;
        this.list_of_subjects = list_of_subjects;
        this.mark = mark;
    }

    public Journal(Long list_of_subjects, Integer mark) {
        this.list_of_subjects = list_of_subjects;
        this.mark = mark;
    }

    public Long getJournal_id() {
        return journal_id;
    }

    public void setJournal_id(Long journal_id) {
        this.journal_id = journal_id;
    }

    public Long getList_of_subjects() {
        return list_of_subjects;
    }

    public void setList_of_subjects(Long list_of_subjects) {
        this.list_of_subjects = list_of_subjects;
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
                ", list_of_subjects=" + list_of_subjects +
                ", mark=" + mark +
                '}';
    }
}
