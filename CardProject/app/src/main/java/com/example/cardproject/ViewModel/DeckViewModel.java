package com.example.cardproject.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cardproject.Entity.Deck;
import com.example.cardproject.Repository.DeckRepository;

import java.util.List;

public class DeckViewModel extends AndroidViewModel {


    private DeckRepository mRepository;
    private LiveData<List<Deck>> mAllDecks;
    private LiveData<Deck> mDeck;


    public DeckViewModel(@NonNull Application application) {
        super(application);
        mRepository = new DeckRepository(application);
        mAllDecks = mRepository.getAllDecks();
    }

    public LiveData<List<Deck>> getAllDecks() { return mAllDecks;}

    public LiveData<Deck> getDeck(int id) {
       return mRepository.getDeck(id);
    }


    public void insert(Deck deck){
        mRepository.insert(deck);
    }


}

