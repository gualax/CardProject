package com.example.cardproject.UI;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

import java.util.ArrayList;

import static androidx.navigation.Navigation.findNavController;

public class CardScreenFragment extends Fragment implements  CardScreenInterface.View{

    static final String TAG = "CardScreenFragment";

    public CardScreenFragment newInstance() {
        return new CardScreenFragment();
    }

    CardScreenPresenter mPresenter;
    RecyclerView mRecyclerView;
    CardListAdapter mCardListAdapter;
    int deckID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.card_screen_fragment, container, false);
        mRecyclerView = rootView.findViewById(R.id.card_recycler_view);

        deckID = getArguments().getInt("deckID");
        Log.e(TAG,"Deck ID: " + deckID );
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numeroColumnas = (int) (dpWidth / 180 );

        mRecyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), numeroColumnas));
        mPresenter = createPresenter(getContext());
        obtainCardData();

        Button addCardsToDeck = rootView.findViewById(R.id.add_cards);

        addCardsToDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDeckCards(deckID);
            }
        });

        return rootView;
    }

    @Override
    public void obtainCardData() {
        Log.e(TAG,"obtainCardData");
        mPresenter.fetchCardData();
    }

    @Override
    public void showCardData(ArrayList<CardPoke> cardPokeData) {
        Log.e(TAG,"Showing card data");
      //  mCardListAdapter.notifyDataSetChanged();
        mCardListAdapter = new CardListAdapter(getContext(),cardPokeData);
        mRecyclerView.setAdapter(mCardListAdapter);

    }

    @Override
    public void changeDeckCards(int deckID) {
        mPresenter.updateDeckWhitCards(deckID,mCardListAdapter.getSelectedCards());

    }

    @Override
    public void backToDecksScreen() {
        findNavController(getView()).navigate(R.id.action_homeCardFragment_to_deckScreenFragment);
    }


    protected CardScreenPresenter createPresenter(Context context) {
        return new CardScreenPresenter(this, new CardPokeInteractor());
    }
}


