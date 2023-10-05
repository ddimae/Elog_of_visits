package com.lavrenko.blog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_group;

    private String num_group1;

    @OneToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    @ManyToMany(mappedBy = "groupsList", fetch = FetchType.LAZY)
    private List<Teacher> teacherList = new ArrayList<>();

    public Groups(){

    }

    public Groups(String num_group1) {
        this.num_group1 = num_group1;
    }

    public Long getId_group() {
        return id_group;
    }

    public void setId_group(Long id_group) {
        this.id_group = id_group;
    }

    public String getNum_group1() {
        return num_group1;
    }

    public void setNum_group1(String num_group1) {
        this.num_group1 = num_group1;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public String toString() {
        return num_group1;
    }
}
