package com.example.pokemon.services;

import com.example.pokemon.dto.PokemonDto;
import com.example.pokemon.dto.PokemonResponse;
import com.example.pokemon.models.Pokemon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PokemonService {

    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponse getAllPokemon(int pageNo, int pageSize);
    PokemonDto getPokemonById(int id);
    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
    void deletePokemonId(int id);

}