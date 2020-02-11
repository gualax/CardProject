package com.example.cardproject.Presenter;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cardproject.Base.BasePresenter;
import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.Entity.Deck;
import com.example.cardproject.Interface.DeckScreenInterface;
import com.example.cardproject.UI.DeckScreenFragment;
import com.example.cardproject.ViewModel.DeckViewModel;

import java.util.ArrayList;

public class DeckScreenPresenter extends BasePresenter implements DeckScreenInterface.Presenter {


    private static final String TAG = "DeckScreenPresenter";
    DeckScreenFragment view;
    private DeckViewModel mDeckViewModel;

    public DeckScreenPresenter(DeckScreenFragment view) {
        this.view = view;
    }
    public DeckScreenPresenter() {
    }

    @Override
    public void deleteDeck(int deckId) {
        mDeckViewModel = new ViewModelProvider( this.view ).get(DeckViewModel.class);
        mDeckViewModel.getDeck(deckId).observe( this.view.getViewLifecycleOwner(), new Observer<Deck>() {
            @Override
            public void onChanged(Deck deck) {
                mDeckViewModel.deleteDeck(deckId);
               // view.backToDecksScreen();
            }
        });
    }
}
