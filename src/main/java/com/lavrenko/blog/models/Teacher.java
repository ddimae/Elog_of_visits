package com.lavrenko.blog.models;

import javax.persistence.*;
import java.util.*;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_teacher;

    private String name_teacher;

    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "teacher_subject",
            joinColumns = {
                    @JoinColumn(name = "id_teacher", referencedColumnName = "id_teacher",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_sub", referencedColumnName = "id_sub",
                            nullable = false, updatable = false)})
    private List<Subject> subjectList = new ArrayList<>();

    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "teacher_group",
            joinColumns = {
                    @JoinColumn(name = "id_teacher", referencedColumnName = "id_teacher",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_group", referencedColumnName = "id_group",
                            nullable = false, updatable = false)})
    private List<Groups> groupsList = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(String name_teacher) {
        this.name_teacher = name_teacher;
    }

    public Long getId_teacher() {
        return id_teacher;
    }

    public void setId_teacher(Long id_teacher) {
        this.id_teacher = id_teacher;
    }

    public String getName_teacher() {
        return name_teacher;
    }

    public void setName_teacher(String name_teacher) {
        this.name_teacher = name_teacher;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<Groups> getGroupsList() {
        return groupsList;
    }

    public void setGroupsList(List<Groups> groupsList) {
        this.groupsList = groupsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id_teacher.equals(teacher.id_teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_teacher);
    }

    @Override
    public String toString() {
        return name_teacher;
    }
}
