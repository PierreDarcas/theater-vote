package com.theater.app.repositories;

import com.theater.app.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    Iterable<Review> findAllByViewerId(Long viewerId);
    Iterable<Review> findAllByPlayId(Long playId);
}
