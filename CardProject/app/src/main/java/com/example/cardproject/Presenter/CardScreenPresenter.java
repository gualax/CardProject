package com.example.cardproject.Presenter;

import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import com.example.cardproject.Base.BasePresenter;
import com.example.cardproject.DataBase.DeckRoomDatabase;
import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.Entity.Deck;
import com.example.cardproject.Interactor.CardPokeInteractor;
import com.example.cardproject.Interface.CardScreenInterface;
import com.example.cardproject.Models.PokemonApiResponse;
import com.example.cardproject.UI.CardScreenFragment;
import com.example.cardproject.ViewModel.DeckViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CardScreenPresenter extends BasePresenter implements CardScreenInterface.Presenter {

    private static final String TAG = "CardScreenPresenter";
    CardPokeInteractor cardPokeInteractor;
    CardScreenFragment view;
    private DeckViewModel mDeckViewModel;


    public interface onResultFetchExtra {
        void onSucces(CardPoke newCardPoke);
        void onFailure();
    }

    public CardScreenPresenter(CardScreenFragment view, CardPokeInteractor cardPokeInteractor) {
        this.view = view;
        this.cardPokeInteractor = cardPokeInteractor;
        this.mDeckViewModel = new ViewModelProvider(this.view).get(DeckViewModel.class);

    }

    @Override
    public void fetchCardDataFromApi() {
        Log.e(TAG, "fetchCardData");
        cardPokeInteractor.remoteFetch(new CardPokeInteractor.onResultFetch() {
            @Override
            public void onSucces(PokemonApiResponse data) {
                Log.d(TAG, "onSucces");
                ArrayList<CardPoke> cardPokeData = data.getCardPokeList();
                //Seteo de id
                cardPokeData.forEach(cardPoke -> {
                    String url = cardPoke.getUrl();
                    int lastIndex = url.lastIndexOf("/");
                    int id = Integer.parseInt(url.substring(34, lastIndex));
                    cardPoke.setId(id);
                });
                view.showCardData(cardPokeData);
            }

            @Override
            public void onFailure() {
                Log.d(TAG, "onFailure");
            }
        });
    }

    @Override
    public void updateDeckWhitCards(int deckId, ArrayList<CardPoke> cardPokeSelected) {

        DeckRoomDatabase.databaseWriteExecutor.execute(() -> {
           Deck deck = mDeckViewModel.getDeckObject(deckId);
           Log.e(TAG,"" + deck.getName());
                ArrayList<CardPoke> updatedCardList = deck.getCardPokes();
                if (updatedCardList == null) {
                    deck.setCardPokes(cardPokeSelected);
                    deck.setCountCards(cardPokeSelected.size());
                } else {
                    updatedCardList.addAll(cardPokeSelected);
                    deck.setCountCards(updatedCardList.size());
                }
                mDeckViewModel.update(deck);
        });
        view.backToDecksScreen();
    }


/*
    @Override
    public void fetchCardDataFromDeck(int deckId) {
        mDeckViewModel.getDeck(deckId).observe(this.view.getViewLifecycleOwner(), new Observer<Deck>() {
            @Override
            public void onChanged(Deck deck) {
                ArrayList<CardPoke> cardPokeData;
                cardPokeData = deck.getCardPokes();
                view.showCardData(cardPokeData);
            }
        });
    }
*/


    public void getExtraInfoPokes(CardPoke cardPoke, onResultFetchExtra listener) {
         cardPokeInteractor.getExtraInfoApi(new CardPokeInteractor.onResultFetchExtra() {
             @Override
             public void onSucces(CardPoke newCardPoke) {
                 Log.e(TAG, "OnSucces type is:"+ newCardPoke.getType());
                 cardPoke.setType(newCardPoke.getType());
                 listener.onSucces(cardPoke);
             }
             @Override
             public void onFailure() {
                Log.d(TAG,"FALLO =( =(");
             }
         },cardPoke);
    }



    public void deleteCardsFromDeck(int deckId, ArrayList<CardPoke> cardSelected) {
        DeckRoomDatabase.databaseWriteExecutor.execute(() -> {
            Deck deck = mDeckViewModel.getDeckObject(deckId);
            List<CardPoke> cardPokes = deck.getCardPokes();

            List<Integer> cardIds = cardSelected.stream()
                    .map(c -> c.getId())
                    .collect(Collectors.toList());
            cardPokes = cardPokes.stream()
                    .filter(c -> !cardIds.contains(c.getId()))
                    .collect(Collectors.toList());

            deck.setCardPokes((ArrayList<CardPoke>) cardPokes);
            deck.setCountCards(cardPokes.size());
            mDeckViewModel.update(deck);
        });
    }
}