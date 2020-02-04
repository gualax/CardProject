package com.example.cardproject.Entity;

public class Deck {

    private int id;
    private Card[] cards;


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

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }
}
