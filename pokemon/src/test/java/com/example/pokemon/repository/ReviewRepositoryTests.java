package com.example.pokemon.repository;

import com.example.pokemon.dao.ReviewRepository;
import com.example.pokemon.models.Pokemon;
import com.example.pokemon.models.Review;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReviewRepositoryTests {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    public void ReviewRepository_SaveAll_ReturnSavedReview() {
        //Arrange
        Review review = Review
                .builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        //Act
        Review savedReview = reviewRepository.save(review);

        //Assert
        Assertions.assertThat(savedReview).isNotNull();
        Assertions.assertThat(savedReview.getId()).isGreaterThan(0);

    }

    @Test
    public void ReviewRepository_getAll_ReturnMoreThanOneReview() {
        //Arrange
        Review review = Review
                .builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        Review review2 = Review
                .builder()
                .title("title2")
                .content("content")
                .stars(5)
                .build();

        reviewRepository.save(review);
        reviewRepository.save(review2);

        //Act
        List<Review> getAllReviews = reviewRepository.findAll();

        //Assert
        Assertions.assertThat(getAllReviews).isNotNull();
        Assertions.assertThat(getAllReviews.size()).isEqualTo(2);
    }

    @Test
    public void ReviewRepository_findById_ReturnsSavedReview() {
        //Arrange
        Review review = Review
                .builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        //Act
        reviewRepository.save(review);
        Review reviewReturn = reviewRepository.findById(review.getId()).get();

        //Assert
        Assertions.assertThat(reviewReturn).isNotNull();
    }

    @Test
    public void ReviewRepository_updateReview_ReturnUpdatedReview() {
        //Arrange
        Review review = Review
                .builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        //Act
        reviewRepository.save(review);
        Review reviewReturn = reviewRepository.findById(review.getId()).get();
        reviewReturn.setTitle("boom");
        reviewReturn.setContent("new");
        Review updatedReview = reviewRepository.save(reviewReturn);

        //Assert
        Assertions.assertThat(updatedReview.getTitle()).isNotNull();
        Assertions.assertThat(updatedReview.getContent()).isNotNull();
    }

    @Test
    public void ReviewRepository_ReviewDelete_ReturnReviewIsEmpty() {
        //Arrange
        Review review = Review
                .builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        //Act
        reviewRepository.save(review);

        reviewRepository.deleteById(review.getId());
        Optional<Review> reviewReturn = reviewRepository.findById(review.getId());

        //Assert
        Assertions.assertThat(reviewReturn).isEmpty();
    }

}