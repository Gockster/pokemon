package com.example.pokemon.dto;

import com.example.pokemon.models.Pokemon;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class PokemonDtoMapper implements Function<Pokemon, PokemonDto> {
    @Override
    public PokemonDto apply(Pokemon pokemon) {
        return new PokemonDto(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getType()
        );
    }
}
