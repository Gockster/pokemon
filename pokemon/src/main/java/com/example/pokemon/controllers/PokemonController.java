package com.example.pokemon.controllers;

import com.example.pokemon.dto.PokemonDto;
import com.example.pokemon.dto.PokemonResponse;
import com.example.pokemon.models.Pokemon;
import com.example.pokemon.services.PokemonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("pokemon")
    public ResponseEntity<PokemonResponse> getPokemons(
            @RequestParam(name="pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(name="pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(pokemonService.getAllPokemon(pageNo, pageSize), HttpStatus.OK);
    }

    @PostMapping("pokemon/create")
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto), HttpStatus.CREATED);
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDto> findPokemonById(@PathVariable int id) {
        return new ResponseEntity<>(pokemonService.getPokemonById(id), HttpStatus.FOUND);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable int id) {
        return new ResponseEntity<>(pokemonService.updatePokemon(pokemonDto, id), HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokemon(@PathVariable int id) {
        pokemonService.deletePokemonId(id);
        return new ResponseEntity<>("Pokemon delete", HttpStatus.FOUND);
    }

}
