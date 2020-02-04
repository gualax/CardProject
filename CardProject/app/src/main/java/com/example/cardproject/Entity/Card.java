package com.example.cardproject.Entity;

public class Card {

    private int id;
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
