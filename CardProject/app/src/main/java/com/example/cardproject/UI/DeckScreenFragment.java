package com.example.cardproject.UI;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardproject.Adapter.DeckListAdapter;
import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.Entity.Deck;
import com.example.cardproject.Utils.Constants;
import com.example.cardproject.ViewModel.DeckViewModel;
import com.example.cardproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static androidx.navigation.Navigation.findNavController;

public class DeckScreenFragment extends Fragment {

    final static String TAG = "DeckScreenFragment";
    RecyclerView mRecyclerView;
    ArrayList<Deck> deckList;
    FloatingActionButton cardfloatingActionButton, deckfloatingActionButton;
    private DeckViewModel mDeckViewModel;
    Deck deck;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.deck_screen_fragment,container,false);
        mRecyclerView = rootView.findViewById(R.id.deck_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        deckList = new ArrayList<Deck>();
        deck = new Deck();

        DeckListAdapter deckListAdapter = new DeckListAdapter(getContext());
        mRecyclerView.setAdapter(deckListAdapter);

        mDeckViewModel = new ViewModelProvider(this).get(DeckViewModel.class);
        mDeckViewModel.getAllDecks().observe(getViewLifecycleOwner(), new Observer<List<Deck>>() {
            @Override
            public void onChanged(List<Deck> deckList) {
                deckListAdapter.setDecksList(deckList);
            }
        });

        cardfloatingActionButton = rootView.findViewById(R.id.card_floatingActionButton);
        deckfloatingActionButton = rootView.findViewById(R.id.deck_floatingActionButton);

        cardfloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Log.d(TAG,"AFAFAAF");

             //findNavController(v).navigate(R.id.action_deckScreenFragment_to_homeCardFragment);

                mDeckViewModel.getDeck(504196307).observe(getViewLifecycleOwner(), new Observer<Deck>() {
                    @Override
                    public void onChanged(Deck deck) {
                        Deck newdeck = new Deck();
                        newdeck = deck;
                        Log.d(TAG,"Deck from room id" + newdeck.getId());
                        Log.d(TAG,"Deck from room name" + newdeck.getName());
                        Log.d(TAG,"Deck from room cardPOKE name" + newdeck.getCardPokes().get(0).getName());

                    }
                });

            }
        });

        deckfloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int id = random.nextInt()  & Integer.MAX_VALUE;
                deck = new Deck(id);
                deck.setName("The big deck");
                ArrayList<CardPoke> cardPokes = new ArrayList<CardPoke>();
                CardPoke cardPoke = new CardPoke();
                cardPoke.setId(2);
                cardPoke.setName("escuero");
                cardPoke.setType(Constants.WATER);
                cardPokes.add(cardPoke);
                deck.setCardPokes(cardPokes);
                mDeckViewModel.insert(deck);
            }
        });

        return rootView;
    }
}
