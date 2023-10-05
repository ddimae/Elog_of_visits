package com.lavrenko.blog.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_rating;

    private Integer grade;

    @JsonFormat
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_sub")
    private Subject subject;

    public Rating() {
    }

    public Rating(Integer grade, Date date) {
        this.grade = grade;
        this.date = date;
    }

    public Long getId_rating() {
        return id_rating;
    }

    public void setId_rating(Long id_rating) {
        this.id_rating = id_rating;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return Objects.equals(id_rating, rating.id_rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_rating);
    }
}
