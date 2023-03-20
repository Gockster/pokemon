package com.example.pokemon.services;

import com.example.pokemon.dto.ReviewDto;
import com.example.pokemon.models.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    ReviewDto createReview(int pokemonId, ReviewDto reviewDto);
    List<ReviewDto> getReviewByPokemonId(int id);
    ReviewDto getReviewById(int reviewId, int pokemonId);
    ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto);
    void deleteReview(int pokemonId, int reviewId);

}

