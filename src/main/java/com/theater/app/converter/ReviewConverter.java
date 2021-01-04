package com.theater.app.converter;

import com.theater.app.dto.ReviewDTO;
import com.theater.app.model.Review;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConverter {

    public ReviewDTO entityToDTO(Review review){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(review, ReviewDTO.class);
    }

    public List<ReviewDTO> entityToDTO(List<Review> reviews){
        return reviews.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    public Review dtoToEntity(ReviewDTO reviewDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(reviewDTO, Review.class);
    }

    public List<Review> dtoToEntity(List<ReviewDTO> reviewDTOS){
        return reviewDTOS.stream().map(this::dtoToEntity).collect(Collectors.toList());
    }

}
