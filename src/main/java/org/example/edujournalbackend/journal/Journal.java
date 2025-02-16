package org.example.edujournalbackend.journal;

import java.time.LocalDate;

public class Journal {
    public Long journal_id;
    public Long list_of_subject_id;
    public Long student_id;
    public String firstname;
    public String lastname;
    public Integer mark;
    public LocalDate date_for;
    public Integer year;
    public Integer month;
    public Integer day;

    public Journal() {
    }

    public Journal(Long journal_id, Long list_of_subject_id, Long student_id, Integer mark, LocalDate date_for) {
        this.journal_id = journal_id;
        this.list_of_subject_id = list_of_subject_id;
        this.student_id = student_id;
        this.mark = mark;
        this.date_for = date_for;
    }

    public Journal(Long student_id, String firstname, String lastname, Integer mark, LocalDate date_for, Integer year, Integer month, Integer day) {
        this.student_id = student_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mark = mark;
        this.date_for = date_for;
        this.year = year;
        this.month = month;
        this.day = day;
    } // Для отправки оценок и их даты с ФИ студента

    public Long getJournal_id() {
        return journal_id;
    }

    public void setJournal_id(Long journal_id) {
        this.journal_id = journal_id;
    }

    public Long getList_of_subject_id() {
        return list_of_subject_id;
    }

    public void setList_of_subject_id(Long list_of_subject_id) {
        this.list_of_subject_id = list_of_subject_id;
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

    public LocalDate getDate_for() {
        return date_for;
    }

    public void setDate_for(LocalDate date_for) {
        this.date_for = date_for;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "journal_id=" + journal_id +
                ", list_of_subject_id=" + list_of_subject_id +
                ", student_id=" + student_id +
                ", mark=" + mark +
                ", date_for=" + date_for +
                '}';
    }
}
