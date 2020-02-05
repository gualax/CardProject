package com.example.cardproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardproject.Entity.Card;
import com.example.cardproject.R;

import java.util.ArrayList;
import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> {

    class CardViewHolder extends RecyclerView.ViewHolder {
        TextView tv_poke_name;

        private CardViewHolder(View itemView) {
            super(itemView);
            tv_poke_name = (TextView) itemView.findViewById(R.id.tv_poke_name);
        }
        public void assignData(final Card card){
            tv_poke_name.setText(card.getPokemon().getName());
        }
    }

    //private final LayoutInflater mInflater;
     List<Card> cardList; // Cached copy of cards

    //public CardListAdapter(Context context) { mInflater = LayoutInflater.from(context); }
    public CardListAdapter(ArrayList<Card> cardList) {
        this.cardList = cardList;
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,null,false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.assignData(cardList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.cardList.size();
    }


    public  void setCards(List<Card> cards){

        cardList = cards;
        notifyDataSetChanged();
    }

}
