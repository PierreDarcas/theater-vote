package com.theater.app.repositories;

import com.theater.app.model.Play;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayRepository extends CrudRepository<Play, Long> {
    void deleteById(Long playId);
}
