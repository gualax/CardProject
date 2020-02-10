package com.example.cardproject.Interface;

import com.example.cardproject.Entity.CardPoke;

import java.util.ArrayList;

public interface CardScreenInterface {

     interface View {
        void obtainCardData();
        void showCardData(ArrayList<CardPoke> cardPokeData);
        void changeDeckCards(int deckID);
        void backToDecksScreen();
    }

    interface Presenter {
        void fetchCardData();
        void updateDeckWhitCards(int deckId, ArrayList<CardPoke> cardPokeSelected);
    }
}
