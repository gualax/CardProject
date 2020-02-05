package com.example.cardproject.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "card_table")

public class Card {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "card")

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
