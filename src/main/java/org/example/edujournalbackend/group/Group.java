package org.example.edujournalbackend.group;

import java.time.LocalDate;

public class Group {
    public Long edu_group_id;

    public Long list_of_subject_id;
    public Long subject_id;

    public String name;
    public LocalDate created;

    public Group() {}

    public Group(Long edu_group_id, String name, LocalDate created) {
        this.edu_group_id = edu_group_id;
        this.name = name;
        this.created = created;
    }

    public Group(String name, LocalDate created) {
        this.name = name;
        this.created = created;
    }

    public Group(Long list_of_subject_id, Long edu_group_id, Long subject_id) {
        this.list_of_subject_id = list_of_subject_id;
        this.edu_group_id = edu_group_id;
        this.subject_id = subject_id;
    }

    public Long getEdu_group_id() {
        return edu_group_id;
    }

    public Long getList_of_subject_id() {
        return list_of_subject_id;
    }

    public void setList_of_subject_id(Long list_of_subject_id) {
        this.list_of_subject_id = list_of_subject_id;
    }

    public Long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(Long subject_id) {
        this.subject_id = subject_id;
    }

    public void setEdu_group_id(Long edu_group_id) {
        this.edu_group_id = edu_group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "edu_group_id=" + edu_group_id +
                ", name='" + name + '\'' +
                ", created=" + created +
                '}';
    }
}
