package com.lavrenko.blog.repo;

import com.lavrenko.blog.models.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubjectRepository extends CrudRepository<Subject,Long> {

}
