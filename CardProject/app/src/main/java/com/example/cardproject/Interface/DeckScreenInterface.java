package com.example.cardproject.Interface;

import com.example.cardproject.Entity.Deck;

import java.util.ArrayList;

public interface DeckScreenInterface {

     interface Presenter{
         void deleteDeck(int deckId);
         void dataDeckChanged(ArrayList<Deck> deckList);
    }
}
