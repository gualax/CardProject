package com.example.cardproject.Presenter;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cardproject.Base.BasePresenter;
import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.Entity.Deck;
import com.example.cardproject.Interactor.CardPokeInteractor;
import com.example.cardproject.Interface.CardScreenInterface;
import com.example.cardproject.Models.PokemonApiResponse;
import com.example.cardproject.UI.CardScreenFragment;
import com.example.cardproject.ViewModel.DeckViewModel;

import java.util.ArrayList;

public class CardScreenPresenter extends BasePresenter implements CardScreenInterface.Presenter {

    private static final String TAG = "CardScreenPresenter";
    CardPokeInteractor cardPokeInteractor;
    CardScreenFragment view;
    private DeckViewModel mDeckViewModel;

    public CardScreenPresenter(CardScreenFragment view, CardPokeInteractor cardPokeInteractor) {
        this.view = view;
        this.cardPokeInteractor = cardPokeInteractor;
    }

    @Override
    public void fetchCardDataFromApi() {
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

    @Override
    public void updateDeckWhitCards(int deckId, ArrayList<CardPoke> cardPokeSelected) {
        mDeckViewModel = new ViewModelProvider( this.view ).get(DeckViewModel.class);
        mDeckViewModel.getDeck(deckId).observe( this.view.getViewLifecycleOwner(), new Observer<Deck>() {
            @Override
            public void onChanged(Deck deck) {
                ArrayList<CardPoke> updatedCardList = deck.getCardPokes();
                if(updatedCardList == null){
                    Log.e(TAG, "la lista de cartas esta vacia");
                    deck.setCardPokes(cardPokeSelected);
                }else {
                    updatedCardList.addAll(cardPokeSelected);
                }
                mDeckViewModel.update(deck);
                view.backToDecksScreen();
            }
        });

    }

    @Override
    public void fetchCardDataFromDeck(int deckId) {
        mDeckViewModel = new ViewModelProvider( this.view ).get(DeckViewModel.class);
        mDeckViewModel.getDeck(deckId).observe( this.view.getViewLifecycleOwner(), new Observer<Deck>() {
            @Override
            public void onChanged(Deck deck) {
                ArrayList<CardPoke> cardPokeData;
                cardPokeData =  deck.getCardPokes();
                view.showCardData(cardPokeData);
            }
        });
    }

       // view.showCardData(cardPokeData);


}
