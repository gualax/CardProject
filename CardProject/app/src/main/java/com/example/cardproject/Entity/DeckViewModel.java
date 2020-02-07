package com.example.cardproject.Entity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cardproject.Repository.DeckRepository;

import java.util.List;

public class DeckViewModel extends AndroidViewModel {


    private DeckRepository mRepository;
    private LiveData<List<Deck>> mAllDecks;


    public DeckViewModel(@NonNull Application application) {
        super(application);
        mRepository = new DeckRepository(application);
        mAllDecks = mRepository.getmAllDecks();
    }

    public LiveData<List<Deck>> getAllDecks() { return mAllDecks;}

    public void insert(Deck deck){
        mRepository.insert(deck);
    }


}

