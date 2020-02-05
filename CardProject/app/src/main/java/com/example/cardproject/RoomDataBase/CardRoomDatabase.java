package com.example.cardproject.RoomDataBase;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.cardproject.Dao.CardDao;
import com.example.cardproject.Entity.Card;
import com.example.cardproject.Interactor.CardPokeInteractor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Card.class}, version = 1, exportSchema = false)
public abstract class CardRoomDatabase extends RoomDatabase implements CardPokeInteractor.onResultFetch {

    public abstract CardDao cardDao();
    private static volatile CardRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CardRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (CardRoomDatabase.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CardRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);


            databaseWriteExecutor.execute(()->{
                CardDao dao = INSTANCE.cardDao();
                CardPokeInteractor cardPokeInteractor = new CardPokeInteractor();
                cardPokeInteractor.remoteFetch();

            //    dao.deleteAll();
/*

                Card word = new Card("Hello");
                dao.insert(word);

                word = new Word("World");
                dao.insert(word);
*/

            });
        }
    };
}
