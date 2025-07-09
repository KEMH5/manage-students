package com.training.integratePostgres.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.training.integratePostgres.school.School;
import com.training.integratePostgres.studentprofile.StudentProfile;
import jakarta.persistence.*;

@Entity
@Table(name = "T_STUDENT")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(
            name = "c_fname",
            length = 20
    )
    private String firsName;

    @Column(
            unique = true
    )
    private String email;

    private String lastName;

    @OneToOne(
            mappedBy = "student",
            cascade = CascadeType.ALL
    )
    private StudentProfile studentProfile;

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public StudentProfile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }

    @ManyToOne
    @JoinColumn(
            name = "school_id"
    )
    @JsonBackReference//Tell the entity that it doesn't need to serialize the parent
    private School school;

    public Student(
            String firsName,
            String lastName,
            String email,
            int age) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student() {
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
