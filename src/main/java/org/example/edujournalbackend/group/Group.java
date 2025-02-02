package org.example.edujournalbackend.group;

import java.time.LocalDate;

public class Group {
    public Long edu_group_id;
    public String name;

    public Group() {}

    public Group(Long edu_group_id, String name, LocalDate created) {
        this.edu_group_id = edu_group_id;
        this.name = name;
    }

    public Group(String name, LocalDate created) {
        this.name = name;
    }

    public Long getEdu_group_id() {
        return edu_group_id;
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
                '}';
    }
}
