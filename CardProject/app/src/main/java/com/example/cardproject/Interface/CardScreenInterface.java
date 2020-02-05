package com.example.cardproject.Interface;

import com.example.cardproject.Entity.Card;

import java.util.ArrayList;

public interface CardScreenInterface {

    interface ViewData {
        void obtainCardData();
        void showCardData(ArrayList<Card> cardData);
    }

    interface Presenter {
        void fetchCardData();
    }
}
