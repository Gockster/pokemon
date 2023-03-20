package com.example.pokemon.dao;

import com.example.pokemon.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon,Integer> {
    @Query("select p from Pokemon p where p.type = ?1")
    Optional<Pokemon> findByType(String type);


}
