package com.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokemonResolver implements GraphQLQueryResolver {

    private final PokemonRepository repository;

    @Autowired
    public PokemonResolver(PokemonRepository repository) {
        this.repository = repository;
    }

    public List<PokemonDocument> getPokemons() {
        return repository.findAll();
    }

    public PokemonDocument getPokemonById(String id) {
        return repository.findById(id).orElseThrow(() -> new PokemonIDNotExistException(id));
    }
}
