package com.example.cardproject.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardproject.Adapter.DeckListAdapter;
import com.example.cardproject.Entity.Deck;
import com.example.cardproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static androidx.navigation.Navigation.findNavController;

public class DeckScreenFragment extends Fragment {

    final static String TAG = "DeckScreenFragment";
    RecyclerView mRecyclerView;
    ArrayList<Deck> deckList;
    DeckListAdapter mAdapter;
    FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.deck_screen_fragment,container,false);
        mRecyclerView = rootView.findViewById(R.id.deck_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));

        deckList = new ArrayList<Deck>();

        floatingActionButton = rootView.findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"AFAFAAF");
                findNavController(v).navigate(R.id.action_deckScreenFragment_to_homeCardFragment);
            }
        });


        return rootView;
    }
}
