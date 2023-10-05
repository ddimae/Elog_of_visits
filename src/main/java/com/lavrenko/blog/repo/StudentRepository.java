package com.lavrenko.blog.repo;

import com.lavrenko.blog.models.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {


}
