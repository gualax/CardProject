package com.example.cardproject.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cardproject.Dao.DeckDao;
import com.example.cardproject.DataBase.DeckRoomDatabase;
import com.example.cardproject.Entity.Deck;

import java.util.List;

public class DeckRepository {

private DeckDao mDeckDao;
private LiveData<List<Deck>> mAllDecks;


public  DeckRepository(Application application){
    DeckRoomDatabase db = DeckRoomDatabase.getDatabase(application);
    mDeckDao = db.deckDao();
    mAllDecks = mDeckDao.getAllDecks();
}

public  LiveData<List<Deck>> getAllDecks() {
    return mAllDecks;
}

public LiveData<Deck> getDeck(int id){
   return mDeckDao.getDeck(id);
}

public void insert(Deck deck){
    DeckRoomDatabase.databaseWriteExecutor.execute(()->{
        mDeckDao.insert(deck);
    });
}

}
