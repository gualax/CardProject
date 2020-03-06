package com.example.cardproject.Repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.LiveData;


import com.example.cardproject.Entity.Deck;
import com.example.cardproject.Presenter.DeckScreenPresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;


public class FireStoreDeck {

    final static String TAG = "FireStoreDeck";
    final FirebaseFirestore firestore;
     DeckScreenPresenter mPresenter;

    public FireStoreDeck() {
        firestore = FirebaseFirestore.getInstance();
    }

    public FireStoreDeck(DeckScreenPresenter presenter) {
        firestore = FirebaseFirestore.getInstance();
        mPresenter = presenter;
    }


    public void insertDeck(Deck deck){
        Log.e(TAG, "INSERTING DECK IN DATABASE");
        firestore.collection("decks").document(String.valueOf(deck.getId())).set(deck);
    }


    public void updateDeck(Deck deck){
        Log.e(TAG, "INSERTING DECK IN DATABASE");
        firestore.collection("decks").document(String.valueOf(deck.getId())).set(deck);
    }

    public void deleteDeck(int id){
        Log.e(TAG, "INSERTING DECK IN DATABASE");
        firestore.collection("decks").document(String.valueOf(id)).delete();
    }

    public void getAllDecks(){
        Log.e(TAG, "GETTING FORM  DECK DATABASE");
        ArrayList<Deck> decks = new ArrayList<>();
        firestore.collection("decks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Deck deck = document.toObject(Deck.class);
                                decks.add(deck);
                                // llamar al presenter para q avise que a la view que hay datos
                                // ver dspues como comvertir el ArrayList  LiveData @_@
                            }
                            mPresenter.dataDeckChanged(decks);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }


}
