package com.codeup.springtest.models;

import javax.persistence.*;

@Entity
@Table(name="dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(11) UNSIGNED")
    private long id;

    @Column(columnDefinition = "TINYINT(3) UNSIGNED", nullable = false, unique = true)
    private int age;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(name = "reside_state",columnDefinition = "CHAR(2) DEFAULT 'XX'")
    private String resideState;

    public Dog() {}

    public Dog(long id, int age, String name, String resideState) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.resideState = resideState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResideState() {
        return resideState;
    }

    public void setResideState(String resideState) {
        this.resideState = resideState;
    }
}
