package com.example.cardproject.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardproject.Adapter.CardListAdapter;
import com.example.cardproject.Entity.Card;
import com.example.cardproject.Entity.Pokemon;
import com.example.cardproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class HomeCardFragment extends Fragment {

    static final String TAG = "HomeCardFragment";
    public HomeCardFragment newInstance(){
        return new HomeCardFragment();
    }
    ArrayList<Card> cardList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.home_fragment,container,false);

        RecyclerView recyclerView = rootView.findViewById(R.id.card_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        populateListByJsonFile(cardList);


        CardListAdapter cardListAdapter =  new CardListAdapter(cardList);
        recyclerView.setAdapter(cardListAdapter);

        return rootView;
    }


    private void populateListByJsonFile(ArrayList<Card> clubList) {
        Log.d(TAG,"populateList");
        InputStream is = getResources().openRawResource(R.raw.sample_card);
        String jsonString = new Scanner(is).useDelimiter("\\A").next();
        Log.d(TAG,jsonString);
        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray jsonArray = json.getJSONArray("clubs");

            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj=new JSONObject(jsonArray.get(i).toString());
                Card card = new Card();
                Pokemon pokemon = new Pokemon();

                card.setId(obj.getInt("id"));
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
