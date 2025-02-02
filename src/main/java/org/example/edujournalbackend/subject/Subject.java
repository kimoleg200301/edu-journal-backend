package org.example.edujournalbackend.subject;

public class Subject {
    public Long subject_id;
    public String name;
    public String subject_code;
    public Integer credits;

    public Subject () {}
    public Subject(Long subject_id, String name, String subject_code, Integer credits) {
        this.subject_id = subject_id;
        this.name = name;
        this.subject_code = subject_code;
        this.credits = credits;
    }
    public Subject(String name, String subject_code, Integer credits) {
        this.name = name;
        this.subject_code = subject_code;
        this.credits = credits;
    }

    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subject_id=" + subject_id +
                ", name='" + name + '\'' +
                ", subject_code='" + subject_code + '\'' +
                ", credits=" + credits +
                '}';
    }
}
