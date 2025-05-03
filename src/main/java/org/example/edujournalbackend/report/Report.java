package org.example.edujournalbackend.report;

import java.time.LocalDate;
import java.time.LocalTime;

public class Report {
    private Long studentID;
    private Long subjectID;
    private LocalDate startSessionDate;
    private LocalTime startSessionTime;
    private LocalDate endSessionDate;
    private LocalTime endSessionTime;

    public Report() {}

    public Report(Long studentID, Long subjectID, LocalDate startSessionDate, LocalTime startSessionTime, LocalDate endSessionDate, LocalTime endSessionTime) {
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.startSessionDate = startSessionDate;
        this.startSessionTime = startSessionTime;
        this.endSessionDate = endSessionDate;
        this.endSessionTime = endSessionTime;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public Long getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Long subjectID) {
        this.subjectID = subjectID;
    }

    public LocalDate getStartSessionDate() {
        return startSessionDate;
    }

    public void setStartSessionDate(LocalDate startSessionDate) {
        this.startSessionDate = startSessionDate;
    }

    public LocalTime getStartSessionTime() {
        return startSessionTime;
    }

    public void setStartSessionTime(LocalTime startSessionTime) {
        this.startSessionTime = startSessionTime;
    }

    public LocalDate getEndSessionDate() {
        return endSessionDate;
    }

    public void setEndSessionDate(LocalDate endSessionDate) {
        this.endSessionDate = endSessionDate;
    }

    public LocalTime getEndSessionTime() {
        return endSessionTime;
    }

    public void setEndSessionTime(LocalTime endSessionTime) {
        this.endSessionTime = endSessionTime;
    }

    @Override
    public String toString() {
        return "Report{" +
                "studentID=" + studentID +
                ", subjectID=" + subjectID +
                ", startSessionDate=" + startSessionDate +
                ", startSessionTime=" + startSessionTime +
                ", endSessionDate=" + endSessionDate +
                ", endSessionTime=" + endSessionTime +
                '}';
    }
}
