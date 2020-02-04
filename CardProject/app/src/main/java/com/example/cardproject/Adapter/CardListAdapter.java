package com.example.cardproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardproject.Entity.Card;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> {

    class CardViewHolder extends RecyclerView.ViewHolder {
        // private final TextView wordItemView;

        private CardViewHolder(View itemView) {
            super(itemView);
            //   wordItemView = itemView.findViewById(R.id.textView);
        }
    }

    //private final LayoutInflater mInflater;
    private List<Card> cardList; // Cached copy of cards

    //public CardListAdapter(Context context) { mInflater = LayoutInflater.from(context); }
    public CardListAdapter(ArrayList<Card> cardList) {
        cardList = cardList;
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
