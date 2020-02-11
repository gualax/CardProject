package com.example.cardproject.Interface;

import com.example.cardproject.Entity.CardPoke;

import java.util.ArrayList;

public interface CardScreenInterface {

     interface View {
        void obtainCardDataFromApi();
        void showCardData(ArrayList<CardPoke> cardPokeData);
        void changeDeckCards(int deckID);
        void backToDecksScreen();
        void obtainDataFromDeck(int decKId);
    }

    interface Presenter {
        void fetchCardDataFromApi();
        void updateDeckWhitCards(int deckId, ArrayList<CardPoke> cardPokeSelected);
        void fetchCardDataFromDeck(int decKId);
    }
}
