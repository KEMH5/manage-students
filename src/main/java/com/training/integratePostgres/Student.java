package com.training.integratePostgres;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_STUDENT")
public class Student {

    @Id
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
