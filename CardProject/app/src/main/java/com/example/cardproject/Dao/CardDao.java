package com.example.cardproject.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.cardproject.Entity.Card;

import java.util.List;

@Dao
public interface CardDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Card card);

    @Query("DELETE FROM card_table")
    void deleteAll();

    @Query("SELECT * from card_table")
    LiveData<List<Card>> getAlCards();

}
