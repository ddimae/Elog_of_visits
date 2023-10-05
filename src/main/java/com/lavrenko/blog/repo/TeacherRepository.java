package com.lavrenko.blog.repo;

import com.lavrenko.blog.models.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

}
