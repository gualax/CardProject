package com.example.cardproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cardproject.Entity.Deck;
import com.example.cardproject.R;

import java.util.List;

public class DeckListAdapter extends RecyclerView.Adapter<DeckListAdapter.DeckViewHolder> {

    List<Deck> deckList;
    private LayoutInflater mInflater = null;

    class DeckViewHolder extends RecyclerView.ViewHolder {
        TextView tv_deck_name;


        private DeckViewHolder(View itemView) {
            super(itemView);
            tv_deck_name = itemView.findViewById(R.id.tv_deck_name);
        }

        public void assignData(final Deck deck){
            tv_deck_name.setText(deck.getName());
        }
    }

    public DeckListAdapter(List<Deck> deckList) {
        this.deckList = deckList;
    }

    public  DeckListAdapter(Context context){
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public DeckListAdapter.DeckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.from(parent.getContext()).inflate(R.layout.deck_item,null,false);
        return new DeckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeckListAdapter.DeckViewHolder holder, int position) {
        holder.assignData(deckList.get(position));
    }

    @Override
    public int getItemCount() {
        if(deckList == null){
            return 0;
        }else {
            return deckList.size();
        }
    }

   public void setDecksList(List<Deck> decks){
        deckList = decks;
        notifyDataSetChanged();
    }

    public void setDeck(Deck deck){
        deckList.add(deck);
        notifyDataSetChanged();
    }

}
