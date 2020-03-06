package com.example.cardproject.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cardproject.Entity.Deck;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DeckDao  {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Deck deck);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertDeckList(ArrayList<Deck> deckList);


    @Query("DELETE FROM deck_table")
    void deleteAll();

    @Update
    void update(Deck deck);

    @Query("SELECT * FROM deck_table")
    LiveData<List<Deck>> getAllDecks();


    @Query("SELECT * FROM deck_table WHERE id=:id")
    LiveData<Deck> getDeck(int id);

    @Query("SELECT * FROM deck_table WHERE id=:id")
     Deck getDeckObject(int id);

    @Query("DELETE FROM deck_table WHERE id=:id")
    void deleteDeck(int id);

    @Query("UPDATE deck_table SET cardPokes = :cards WHERE id = :deckId")
    void update(int deckId, String cards);
}
