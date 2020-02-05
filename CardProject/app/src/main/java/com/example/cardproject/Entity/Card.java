package com.example.cardproject.Entity;

import com.google.gson.annotations.SerializedName;

public class Card {

    @SerializedName("id")
    private int id;

    @SerializedName("pokemon")
    private Pokemon pokemon;


    public Card() {}

    public Card(int id) {
        this.id = id;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
