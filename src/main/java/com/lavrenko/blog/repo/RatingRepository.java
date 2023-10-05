package com.lavrenko.blog.repo;

import com.lavrenko.blog.models.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Long> {
}
