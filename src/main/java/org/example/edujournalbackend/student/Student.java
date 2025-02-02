package org.example.edujournalbackend.student;

import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDate;
import java.util.Optional;

public class Student {
    private Long student_id;
    private String firstname;
    private String lastname;
    private LocalDate birth_date;
    private String gender;
    private String iin;
    private String living_adress;
    private Optional<Long> edu_group_id;

    public Student() {}
    public Student(String firstname,
                    String lastname,
                    LocalDate birth_date,
                    String gender,
                    String iin,
                    String living_adress,
                    Optional<Long> edu_group_id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth_date = birth_date;
        this.gender = gender;
        this.iin = iin;
        this.living_adress = living_adress;
        this.edu_group_id = edu_group_id;
    }

    public Student(Long student_id,
                   String firstname,
                   String lastname,
                   LocalDate birth_date,
                   String gender,
                   String iin,
                   String living_adress,
                   Optional<Long> edu_group_id) {
        this.student_id = student_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth_date = birth_date;
        this.gender = gender;
        this.iin = iin;
        this.living_adress = living_adress;
        this.edu_group_id = edu_group_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getLiving_adress() {
        return living_adress;
    }

    public void setLiving_adress(String living_adress) {
        this.living_adress = living_adress;
    }

    public Optional<Long> getEdu_group_id() { return edu_group_id; }

    public void setEdu_group_id(Optional<Long> edu_group_id) { this.edu_group_id = edu_group_id; }

    @Override
    public String toString() {
        return "Students{" +
                "student_id=" + student_id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birth_date=" + birth_date +
                ", gender='" + gender + '\'' +
                ", iin='" + iin + '\'' +
                ", living_adress='" + living_adress + '\'' +
                ", edu_group_id=" + edu_group_id +
                '}';
    }
}
