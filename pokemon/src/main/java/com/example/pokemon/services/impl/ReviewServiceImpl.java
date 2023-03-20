package com.example.pokemon.services.impl;

import com.example.pokemon.dao.PokemonRepository;
import com.example.pokemon.dao.ReviewRepository;
import com.example.pokemon.dto.ReviewDto;
import com.example.pokemon.exceptions.PokemonNotFoundException;
import com.example.pokemon.exceptions.ReviewNotFoundException;
import com.example.pokemon.models.Pokemon;
import com.example.pokemon.models.Review;
import com.example.pokemon.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final PokemonRepository pokemonRepository;


    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found"));
        review.setPokemon(pokemon);
        Review newReview = reviewRepository.save(review);
        return mapToDto(newReview);
    }

    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        return review;
    }

    @Override
    public List<ReviewDto> getReviewByPokemonId(int id) {
        List<Review> reviews = reviewRepository.findByPokemon_Id(id);
        return reviews
                .stream()
                .map(review -> mapToDto(review))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(int reviewId, int pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associate pokemon not found"));
        if (review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This review does not belong to the pokemon");
        }
        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associate pokemon not found"));
        if (review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This review does not belong to the pokemon");
        }
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        Review updateDto = reviewRepository.save(review);

        return mapToDto(updateDto);
    }

    @Override
    public void deleteReview(int pokemonId, int reviewId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associate pokemon not found"));
        if (review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("This review does not belong to the pokemon");
        }
        reviewRepository.delete(review);

    }

    private ReviewDto mapToDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }

}
