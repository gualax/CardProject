package com.example.cardproject.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cardproject.Dao.DeckDao;
import com.example.cardproject.DataBase.Converters;
import com.example.cardproject.DataBase.DeckRoomDatabase;
import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.Entity.Deck;

import java.util.ArrayList;
import java.util.List;

public class DeckRepository {

    private DeckDao mDeckDao;
    private LiveData<List<Deck>> mAllDecks;


    public DeckRepository(Application application) {
        DeckRoomDatabase db = DeckRoomDatabase.getDatabase(application);
        mDeckDao = db.deckDao();
        mAllDecks = mDeckDao.getAllDecks();
    }

    public LiveData<List<Deck>> getAllDecks() {
        return mAllDecks;
    }

    public LiveData<Deck> getDeck(int id) {
        return mDeckDao.getDeck(id);
    }

    public Deck getDeckObject(int id){
        return mDeckDao.getDeckObject(id);
    }

    public void deleteDeck(int id) {
        DeckRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDeckDao.deleteDeck(id);
        });
    }

    public void insert(Deck deck) {
        DeckRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDeckDao.insert(deck);
        });
    }


    public void insertDeckList(ArrayList<Deck> deckList) {
        DeckRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDeckDao.insertDeckList(deckList);
        });
    }


    public void update(Deck deck) {
        DeckRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDeckDao.update(deck);
        });
    }

    public void update(int deckId, ArrayList<CardPoke> cardPokeSelected) {
        DeckRoomDatabase.databaseWriteExecutor.execute(() -> {
            String cards = new Converters().stringFromObject(cardPokeSelected);
            mDeckDao.update(deckId, cards);
        });
    }
}
