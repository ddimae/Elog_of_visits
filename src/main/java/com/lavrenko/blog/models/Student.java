package com.lavrenko.blog.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id_student;

    private String name_student;
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "id_group")
    private Groups groups;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Rating> ratings = new ArrayList<>();

    public Long getId_student() {
        return id_student;
    }

    public void setId_student(Long id_student) {
        this.id_student = id_student;
    }

    public String getName_student() {
        return name_student;
    }

    public void setName_student(String name_student) {
        this.name_student = name_student;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Groups getGroups() { return groups; }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public Student() {
    }

    public Student(String name_student, String description) {
        this.name_student = name_student;
        this.description = description;
    }

    @Override
    public String toString() {
        return name_student;
    }
}