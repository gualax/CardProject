package com.example.cardproject.Models;

import com.example.cardproject.Entity.Pokemon;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonApiResponse {



    @SerializedName("pokemonList")
    List<Pokemon> pokemonList;

    public PokemonApiResponse(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }
}
