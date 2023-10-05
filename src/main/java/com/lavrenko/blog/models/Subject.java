package com.lavrenko.blog.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Subject {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id_sub;

    private String name_sub;

    @ManyToMany(mappedBy = "subjectList", fetch = FetchType.LAZY)
    private List<Teacher> teachers = new ArrayList<>();

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private List<Rating> ratings = new ArrayList<>();

    public Subject(){
    }

    public Subject(String name_sub) {
        this.name_sub = name_sub;
    }

    public Long getId_sub() {
        return id_sub;
    }

    public void setId_sub(Long id_sub) {
        this.id_sub = id_sub;
    }

    public String getName_sub() {
        return name_sub;
    }

    public void setName_sub(String name_sub) {
        this.name_sub = name_sub;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id_sub.equals(subject.id_sub);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_sub);
    }

    @Override
    public String toString() {
        return name_sub;
    }
}
