package com.example.cardproject.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardproject.Adapter.CardListAdapter;
import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.Interactor.CardPokeInteractor;
import com.example.cardproject.Interface.CardScreenInterface;
import com.example.cardproject.Presenter.CardScreenPresenter;
import com.example.cardproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardScreenFragment extends Fragment implements  CardScreenInterface.View{

    static final String TAG = "CardScreenFragment";

    public CardScreenFragment newInstance() {
        return new CardScreenFragment();
    }

    CardScreenPresenter mPresenter;
    RecyclerView mRecyclerView;
    CardListAdapter mCardListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        mRecyclerView = rootView.findViewById(R.id.card_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), 2));
        mPresenter = createPresenter(getContext());
        obtainCardData();

        //fill adapter
/*        mCardListAdapter = new CardListAdapter(cardPokeList);
        mRecyclerView.setAdapter(mCardListAdapter);*/

        return rootView;
    }

/*
    private void populateListByJsonFile(ArrayList<CardPoke> clubList) {
        Log.d(TAG, "populateList");
        InputStream is = getResources().openRawResource(R.raw.sample_card);
        String jsonString = new Scanner(is).useDelimiter("\\A").next();
        Log.d(TAG, jsonString);
        try {
            JSONObject json = new JSONObject(jsonString);
            JSONArray jsonArray = json.getJSONArray("cards");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = new JSONObject(jsonArray.get(i).toString());
                CardPoke cardPoke = new CardPoke();
                cardPoke.setId(obj.getInt("id"));
                Log.d(TAG, String.valueOf(obj.getInt("id")));
                cardPoke.setName(obj.getString("name"));
                cardPokeList.add(cardPoke);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
*/
    @Override
    public void obtainCardData() {
        Log.e(TAG,"obtainCardData");
        mPresenter.fetchCardData();
    }

    @Override
    public void showCardData(ArrayList<CardPoke> cardPokeData) {
        Log.e(TAG,"Showing card data");
      //  mCardListAdapter.notifyDataSetChanged();
        mCardListAdapter = new CardListAdapter(cardPokeData);
        mRecyclerView.setAdapter(mCardListAdapter);

    }


    protected CardScreenPresenter createPresenter(Context context) {
        return new CardScreenPresenter(this, new CardPokeInteractor());
    }
}


