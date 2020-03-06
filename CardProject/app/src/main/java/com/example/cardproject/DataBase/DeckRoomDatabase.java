package com.example.cardproject.DataBase;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.cardproject.Dao.DeckDao;
import com.example.cardproject.Entity.Deck;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Deck.class}, version = 4)
@TypeConverters({Converters.class})
public abstract class DeckRoomDatabase extends RoomDatabase {

    final static String TAG = "DeckRoomDatabase";
    public abstract DeckDao deckDao();
    private static volatile DeckRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public  static final ExecutorService databaseWriteExecutor =  Executors.newFixedThreadPool(NUMBER_OF_THREADS);



    public static DeckRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (DeckRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DeckRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
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
              //Borra la tabla al iniciar
              DeckDao deckDao = INSTANCE.deckDao();
              deckDao.deleteAll();
            });
        }
    };

}
