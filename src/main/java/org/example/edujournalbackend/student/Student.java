package org.example.edujournalbackend.student;

import java.time.LocalDate;

public class Student {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private String gender;
    private String iin;
    private String livingAdress;

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
                   LocalDate birthDate,
                   String gender,
                   String iin,
                   String livingAdress) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.iin = iin;
        this.livingAdress = livingAdress;
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
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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
        return livingAdress;
    }

    public void setLivingAdress(String livingAdress) {
        this.livingAdress = livingAdress;
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", iin='" + iin + '\'' +
                ", livingAdress='" + livingAdress + '\'' +
                '}';
    }
}
