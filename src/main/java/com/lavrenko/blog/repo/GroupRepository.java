package com.lavrenko.blog.repo;

import com.lavrenko.blog.models.Groups;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Groups, Long> {
}
