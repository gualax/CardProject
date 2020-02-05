package com.example.cardproject.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.cardproject.Dao.CardDao;
import com.example.cardproject.Entity.Card;
import com.example.cardproject.RoomDataBase.CardRoomDatabase;

import java.util.List;

public class CardRepository {

    private CardDao mCardDao;
    private LiveData<List<Card>> mAllCards;

    public CardRepository(Application application){
        CardRoomDatabase db = CardRoomDatabase.getDatabase(application);
        mCardDao = db.cardDao();
        mAllCards = mCardDao.getAlCards();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Card>> getAllCards() {
        return mAllCards;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Card card) {
        CardRoomDatabase.databaseWriteExecutor.execute(() ->{
            mCardDao.insert(card);
        });
    }

}
