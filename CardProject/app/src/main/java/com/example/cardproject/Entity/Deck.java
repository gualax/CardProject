package com.example.cardproject.Entity;

public class Deck {

    private int id;
    private CardPoke[] cardPokes;


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

    public CardPoke[] getCardPokes() {
        return cardPokes;
    }

    public void setCardPokes(CardPoke[] cardPokes) {
        this.cardPokes = cardPokes;
    }
}
