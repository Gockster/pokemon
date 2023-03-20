package com.example.pokemon.models;

import com.example.pokemon.dao.PokemonRepository;
import com.example.pokemon.dao.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Config {


//    @Bean
//    public CommandLineRunner commandLineRunner(PokemonRepository pokemonRepository){
//        return args -> {
//            Review rev1;
//            Pokemon squirtle = new Pokemon(1, "squirtle", "water");
//            Pokemon picatchu = new Pokemon(2, "picatchu", "electric");
//            Pokemon charmander = new Pokemon(3, "charmander", "fire");
//
//            pokemonRepository.saveAll(List.of(squirtle, picatchu, charmander));
//
//        };
//    }
//
//    @Bean
//    public CommandLineRunner commandLineRunner2(ReviewRepository reviewRepository){
//        return args -> {
//
//            Review rev1 = new Review(1, "good", "ok", 4);
//            Review rev2 = new Review(2, "bad", "oka", 4);
//
//            reviewRepository.saveAll(List.of(rev1, rev2));
//        };
//    }
}
