package com.example.cardproject.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cardproject.Entity.Card;
import com.example.cardproject.Repository.CardRepository;

import java.util.List;

public class CardViewModel extends AndroidViewModel {

    private LiveData<List<Card>> mAllCards;
    private CardRepository mRepository;

    public CardViewModel (Application application){
        super(application);
        this.mRepository =  new CardRepository(application);
        this.mAllCards = mRepository.getAllCards();
    }

    public LiveData<List<Card>> getAllCards()
    {
        return mAllCards;
    }

    public void insert(Card card){
        mRepository.insert(card);
    }

}
