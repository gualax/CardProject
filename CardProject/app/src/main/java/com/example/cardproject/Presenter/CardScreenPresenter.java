package com.example.cardproject.Presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.cardproject.Base.BasePresenter;
import com.example.cardproject.Entity.Card;
import com.example.cardproject.Entity.Pokemon;
import com.example.cardproject.Interactor.CardPokeInteractor;
import com.example.cardproject.Interface.CardScreenInterface;
import com.example.cardproject.Models.PokemonApiResponse;
import com.example.cardproject.ui.CardScreenFragment;

import java.util.ArrayList;
import java.util.List;

public class CardScreenPresenter extends BasePresenter implements CardScreenInterface.Presenter {

    private static final String TAG = "CardScreenPresenter";
   // ViewData CardScreenInterface.ViewData;
    CardPokeInteractor cardPokeInteractor;
    CardScreenFragment view;

    public CardScreenPresenter(CardScreenFragment view, CardPokeInteractor cardPokeInteractor) {
        this.view = view;
        this.cardPokeInteractor = cardPokeInteractor;
    }

    @Override
    public void fetchCardData() {
        Log.e(TAG,"fetchCardData");
        cardPokeInteractor.remoteFetch(new CardPokeInteractor.onResultFetch() {
            @Override
            public void onSucces(PokemonApiResponse data) {
                Log.d(TAG,"onSucces");
                ArrayList<Card> cardData = null;
                List<Pokemon> pokemonList = data.getPokemonList();
                view.showCardData(cardData);
            }

            @Override
            public void onFailure() {
                Log.d(TAG,"onFailure");

            }
        });

    }
}
