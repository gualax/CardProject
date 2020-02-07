package com.example.cardproject.Entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "deck_table")

public class Deck {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "deck")
    private int id;
    private String name;
    @Ignore
    private ArrayList<CardPoke> cardPokes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck(){}

    public Deck(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<CardPoke> getCardPokes() {
        return cardPokes;
    }

    public void setCardPokes(ArrayList<CardPoke> cardPokes) {
        this.cardPokes = cardPokes;
    }
}
