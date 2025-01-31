package org.example.edujournalbackend.student;

import java.time.LocalDate;
import java.util.Optional;

public class Student {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birth_date;
    private String gender;
    private String iin;
    private String living_adress;
    private Optional<Long> edu_group_id;

//    public Students(String firstname,
//                    String lastname,
//                    LocalDate birthDate,
//                    String gender,
//                    String iin,
//                    String livingAdress) {
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.birthDate = birthDate;
//        this.gender = gender;
//        this.iin = iin;
//        this.livingAdress = livingAdress;
//    }

    public Student(Long id,
                   String firstname,
                   String lastname,
                   LocalDate birth_date,
                   String gender,
                   String iin,
                   String living_adress,
                   Optional<Long> edu_group_id) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birth_date = birth_date;
        this.gender = gender;
        this.iin = iin;
        this.living_adress = living_adress;
        this.edu_group_id = edu_group_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getBirthDate() {
        return birth_date;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birth_date = birthDate;
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

    public String getLivingAdress() {
        return living_adress;
    }

    public void setLivingAdress(String livingAdress) {
        this.living_adress = livingAdress;
    }

    public Optional<Long> getEdu_group_id() { return edu_group_id; }

    public void setEdu_group_id(Optional<Long> edu_group_id) { this.edu_group_id = edu_group_id; }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthDate=" + birth_date +
                ", gender='" + gender + '\'' +
                ", iin='" + iin + '\'' +
                ", livingAdress='" + living_adress + '\'' +
                ", edu_group_id=" + edu_group_id +
                '}';
    }
}
