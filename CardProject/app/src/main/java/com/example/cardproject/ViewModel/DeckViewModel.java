package com.example.cardproject.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.Entity.Deck;
import com.example.cardproject.Repository.DeckRepository;
import com.example.cardproject.Repository.FireStoreDeck;

import java.util.ArrayList;
import java.util.List;

public class DeckViewModel extends AndroidViewModel {


    private DeckRepository mRepository;
    private LiveData<List<Deck>> mAllDecks;
    private LiveData<Deck> mDeck;
    FireStoreDeck fireStoreDeck;


    public DeckViewModel(@NonNull Application application) {
        super(application);
        mRepository = new DeckRepository(application);
        fireStoreDeck = new FireStoreDeck();
        mAllDecks = mRepository.getAllDecks();
    }

    public LiveData<List<Deck>> getAllDecks() { return mAllDecks;}



    public LiveData<Deck> getDeck(int id) {
       return mRepository.getDeck(id);
    }


    public void insert(Deck deck){
        fireStoreDeck.insertDeck(deck); // inserta el deck en la base de datos de firebase
        mRepository.insert(deck);
    }

    public Deck getDeckObject(int id){
        return  mRepository.getDeckObject(id);
    }

    public void update(Deck deck){
        mRepository.update(deck);
        fireStoreDeck.updateDeck(deck); // inserta el deck en la base de datos de firebase
    }

    public void deleteDeck(int id){
        fireStoreDeck.deleteDeck(id);
        mRepository.deleteDeck(id);
    }


    /*public void update(int deckId, ArrayList<CardPoke> cardPokes) {
        fireStoreDeck.updateDeckById(deckId,cardPokes);
        this.mRepository.update(deckId, cardPokes);
    }*/
}

