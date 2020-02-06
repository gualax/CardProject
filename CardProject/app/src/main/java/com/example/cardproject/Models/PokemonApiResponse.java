package com.example.cardproject.Models;

import com.example.cardproject.Entity.CardPoke;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PokemonApiResponse {

    @SerializedName("count")
    int count;
    @SerializedName("results")
    ArrayList<CardPoke> cardPokeList;


    public int getCount() {
        return count;
    }

    public PokemonApiResponse(ArrayList<CardPoke> cardPokeList) {
        this.cardPokeList = cardPokeList;
    }

    public ArrayList<CardPoke> getCardPokeList() {
        return cardPokeList;
    }

    public void setCardPokeList(ArrayList<CardPoke> pokemonList) {
        this.cardPokeList = pokemonList;
    }
}
