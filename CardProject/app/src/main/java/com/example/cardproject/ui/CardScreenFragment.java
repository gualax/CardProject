package com.example.cardproject.ui;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardproject.Adapter.CardListAdapter;
import com.example.cardproject.Base.BaseFragment;
import com.example.cardproject.Entity.Card;
import com.example.cardproject.Entity.Pokemon;
import com.example.cardproject.Interactor.CardPokeInteractor;
import com.example.cardproject.Interface.CardScreenInterface;
import com.example.cardproject.R;
import com.example.cardproject.ViewModel.CardViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardScreenFragment extends Fragment {

    static final String TAG = "CardScreenFragment";

    public CardScreenFragment newInstance() {
        return new CardScreenFragment();
    }

    ArrayList<Card> cardList;
    private CardViewModel mCardViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        cardList = new ArrayList<Card>();
        RecyclerView recyclerView = rootView.findViewById(R.id.card_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), 2));

        // retrofit call
        /*
        CardPokeInteractor cardPokeInteractor = new CardPokeInteractor();
        cardPokeInteractor.remoteFetch();
*/
        CardListAdapter cardListAdapter = new CardListAdapter(cardList);


        mCardViewModel = new ViewModelProvider(this).get(CardViewModel.class);
       // mCardViewModel.getAllCards().removeObservers(this);
        mCardViewModel.getAllCards().observe(getViewLifecycleOwner(), new Observer<List<Card>>() {
            @Override
            public void onChanged(List<Card> words) {
                cardListAdapter.setCards(words);
            }
        });

        //mock
        populateListByJsonFile(cardList);

        //fill adapter
        recyclerView.setAdapter(cardListAdapter);

        return rootView;
    }


    private void populateListByJsonFile(ArrayList<Card> clubList) {
        Log.d(TAG, "populateList");
        InputStream is = getResources().openRawResource(R.raw.sample_card);
        String jsonString = new Scanner(is).useDelimiter("\\A").next();
        Log.d(TAG, jsonString);
        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray jsonArray = json.getJSONArray("cards");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = new JSONObject(jsonArray.get(i).toString());
                Card card = new Card();
                Pokemon pokemon = new Pokemon();

                card.setId(obj.getInt("id"));
                Log.d(TAG, String.valueOf(obj.getInt("id")));
                JSONObject objPoke = obj.getJSONObject("pokemon");
                pokemon.setId(objPoke.getInt("id"));
                pokemon.setName(objPoke.getString("name"));
                card.setPokemon(pokemon);
                cardList.add(card);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}


