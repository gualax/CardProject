package com.example.cardproject.UI;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardproject.Adapter.DeckListAdapter;
import com.example.cardproject.Entity.Deck;
import com.example.cardproject.Presenter.DeckScreenPresenter;
import com.example.cardproject.Repository.FireStoreDeck;
import com.example.cardproject.ViewModel.DeckViewModel;
import com.example.cardproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DeckScreenFragment extends Fragment {

    final static String TAG = "DeckScreenFragment";
    RecyclerView mRecyclerView;
    ArrayList<Deck> deckList;
    FloatingActionButton  deckfloatingActionButton;
    private DeckViewModel mDeckViewModel;
    DeckScreenPresenter deckScreenPresenter;
    Deck deck;
    DeckListAdapter deckListAdapter;
    boolean firstInit = true;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.deck_screen_fragment,container,false);
        mRecyclerView = rootView.findViewById(R.id.deck_recycler_view);

        //Init Firebase
        FirebaseApp.initializeApp(getContext());
        //trigger FCM token fetching to be able to get token readily
        FirebaseInstanceId.getInstance().getToken();

        mDeckViewModel = new ViewModelProvider(this).get(DeckViewModel.class);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        deckScreenPresenter = new DeckScreenPresenter(this,mDeckViewModel);
        deckList = new ArrayList<Deck>();
        deck = new Deck();

        deckListAdapter = new DeckListAdapter(getContext(), deckScreenPresenter);
        mRecyclerView.setAdapter(deckListAdapter);

        /////////////////////// OBTENER DECKS DE DAO LOCAL ///////////////////////////////
        mDeckViewModel.getAllDecks().observe(getViewLifecycleOwner(), new Observer<List<Deck>>() {
            @Override
            public void onChanged(List<Deck> deckList) {
                deckListAdapter.setDecksList(deckList);
            }
        });

        if(firstInit) {
            /////////////////////// OBTENER DECKS DE FIREBASE //////////////
            FireStoreDeck fireStoreDeck = new FireStoreDeck(deckScreenPresenter);
            fireStoreDeck.getAllDecks();
            firstInit = false;
        }

        deckfloatingActionButton = rootView.findViewById(R.id.deck_floatingActionButton);
        deckfloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return rootView;
    }


    public void showDataChanged(ArrayList<Deck> deckList){
        Log.e(TAG, "showDataChanged");
        deckListAdapter.setDecksList(deckList);
    }


    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Crear un nuevo mazo");
        builder.setMessage("Escriba el nombre del nuevo mazo");
        final EditText input = new EditText(getContext());
        builder.setView(input);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                String textValue = input.getText().toString();
                Random random = new Random();
                int deckId = random.nextInt()  & Integer.MAX_VALUE;
                deck = new Deck(deckId);
                deck.setName(textValue);
                mDeckViewModel.insert(deck);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



}
