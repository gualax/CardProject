package com.example.cardproject.Presenter;

import android.util.Log;

import com.example.cardproject.Base.BasePresenter;
import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.Interactor.CardPokeInteractor;
import com.example.cardproject.Interface.CardScreenInterface;
import com.example.cardproject.Models.PokemonApiResponse;
import com.example.cardproject.ui.CardScreenFragment;
import java.util.ArrayList;

public class CardScreenPresenter extends BasePresenter implements CardScreenInterface.Presenter {

    private static final String TAG = "CardScreenPresenter";
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
                ArrayList<CardPoke> cardPokeData = data.getCardPokeList();

                //Seteo de id
                cardPokeData.forEach(cardPoke -> {
                  String url =  cardPoke.getUrl();
                  int lastIndex = url.lastIndexOf("/");
                  int id = Integer.parseInt(url.substring(34,lastIndex));
                  cardPoke.setId(id);
                });

                view.showCardData(cardPokeData);
            }

            @Override
            public void onFailure() {
                Log.d(TAG,"onFailure");
            }
        });

    }
}
