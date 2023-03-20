package com.example.pokemon.dao;

import com.example.pokemon.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("select r from Review r where r.pokemon.id = ?1")
    List<Review> findByPokemon_Id(int id);
}
