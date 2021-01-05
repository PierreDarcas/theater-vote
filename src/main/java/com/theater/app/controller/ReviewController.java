package com.theater.app.controller;

import com.theater.app.converter.ReviewConverter;
import com.theater.app.dto.ReviewDTO;
import com.theater.app.model.Review;
import com.theater.app.repositories.PlayRepository;
import com.theater.app.repositories.ReviewRepository;
import com.theater.app.repositories.ViewerRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/review")
public class ReviewController {

    private final ReviewConverter reviewConverter;
    private final ReviewRepository reviewRepository;
    private final ViewerRepository viewerRepository;
    private final PlayRepository playRepository;

    @Autowired
    public ReviewController(ReviewConverter reviewConverter, ReviewRepository reviewRepository, ViewerRepository viewerRepository, PlayRepository playRepository) {
        this.reviewConverter = reviewConverter;
        this.reviewRepository = reviewRepository;
        this.viewerRepository = viewerRepository;
        this.playRepository = playRepository;
    }

    @GetMapping
    public List<ReviewDTO> findAll(){
        Iterable<Review> reviews = this.reviewRepository.findAll();
        if (IterableUtils.size(reviews) > 0){
            return this.reviewConverter.entityToDTO((IterableUtils.toList(reviews)));
        }else {
            throw new NoSuchElementException("Aucune critique");
        }
    }

    @GetMapping(path = "/{reviewId}")
    public ReviewDTO findReviewById(@PathVariable("reviewId") Long reviewId){
        Optional<Review> review = this.reviewRepository.findById(reviewId);
        if(review.isPresent()){
            return this.reviewConverter.entityToDTO(review.get());
        }else {
            throw new NoSuchElementException("Critique inexistante");
        }
    }

    @GetMapping(path = "/viewer/{viewerId}")
    public Iterable<ReviewDTO> findReviewsByViewer(@PathVariable("viewerId") Long viewerId){
        if(!viewerRepository.existsById(viewerId)){
            throw new NoSuchElementException("Spectateur inexistant");
        }
        return this.reviewConverter.entityToDTO((List<Review>) this.reviewRepository.findAllByViewerId(viewerId));
    }

    @GetMapping(path = "/play/{playId}")
    public Iterable<ReviewDTO> findReviewsByPlays(@PathVariable("playId") Long playId){
        if(!viewerRepository.existsById(playId)){
            throw new NoSuchElementException("Spectateur inexistant");
        }
        return this.reviewConverter.entityToDTO((List<Review>) this.reviewRepository.findAllByPlayId(playId));
    }

    @PostMapping(path ="/")
    public ReviewDTO createReview(@RequestBody ReviewDTO newReviewDTO){
            return reviewConverter.entityToDTO(reviewRepository.save(this.reviewConverter.dtoToEntity(newReviewDTO)));
    }

    @PutMapping(path ="/{reviewId}")
    public ReviewDTO updateReview(@PathVariable("reviewId") Long reviewId, @RequestBody ReviewDTO newReviewDTO){
        Optional<Review> review = this.reviewRepository.findById(reviewId);
        if(!review.isPresent()){
            throw new NoSuchElementException("Critique inexistante");
        }else if(review.get().getId() != newReviewDTO.getIdReview()){
            throw new IllegalArgumentException();
        }else{
            return  reviewConverter.entityToDTO(reviewRepository.save(this.reviewConverter.dtoToEntity(newReviewDTO)));
        }
    }

    @DeleteMapping(path = "/{reviewId}")
    public void deleteById(@PathVariable("reviewId") Long reviewId){
        this.reviewRepository.deleteById(reviewId);
    }
}
