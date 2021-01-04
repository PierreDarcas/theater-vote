package com.theater.app.controller;

import com.theater.app.converter.ReviewConverter;
import com.theater.app.dto.ReviewDTO;
import com.theater.app.model.Review;
import com.theater.app.model.Viewer;
import com.theater.app.repositories.ReviewRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/review")
public class ReviewController {

    private final ReviewConverter reviewConverter;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewController(ReviewConverter reviewConverter, ReviewRepository reviewRepository) {
        this.reviewConverter = reviewConverter;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping
    public List<ReviewDTO> findAll(){
        Iterable<Review> reviews = this.reviewRepository.findAll();
        if (IterableUtils.size(reviews) > 0){
            return this.reviewConverter.entityToDTO((IterableUtils.toList(reviews)));
        }else {
            throw new NoSuchElementException("Aucune critiques");
        }
    }
}
