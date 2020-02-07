package com.example.cardproject.UI;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

import java.util.ArrayList;

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
        View rootView = inflater.inflate(R.layout.card_screen_fragment, container, false);
        mRecyclerView = rootView.findViewById(R.id.card_recycler_view);

        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numeroColumnas = (int) (dpWidth / 180 );

        mRecyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), numeroColumnas));
        mPresenter = createPresenter(getContext());
        obtainCardData();

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
        mCardListAdapter = new CardListAdapter(cardPokeData);
        mRecyclerView.setAdapter(mCardListAdapter);

    }


    protected CardScreenPresenter createPresenter(Context context) {
        return new CardScreenPresenter(this, new CardPokeInteractor());
    }
}


