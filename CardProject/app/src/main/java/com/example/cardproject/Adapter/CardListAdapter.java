package com.example.cardproject.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.R;

import java.util.ArrayList;
import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> {

    static final String TAG = "CardListAdapter";
    static final String UrlImage = "https://pokeres.bastionbot.org/images/pokemon/";
    class CardViewHolder extends RecyclerView.ViewHolder {
        TextView tv_poke_name,tv_nro_id;
        ImageView img_poke;
        CardView card_poke_view;

        private CardViewHolder(View itemView) {
            super(itemView);
            tv_poke_name = itemView.findViewById(R.id.tv_poke_name);
            img_poke = itemView.findViewById(R.id.img_poke);
            card_poke_view = itemView.findViewById(R.id.card_poke_view);
            tv_nro_id = itemView.findViewById(R.id.tv_nro_id);
        }

        public void assignData(final CardPoke cardPoke){
          tv_poke_name.setText(cardPoke.getName());
          tv_nro_id.setText("Nro:" + String.valueOf(cardPoke.getId()));
          String url =  UrlImage + String.valueOf(cardPoke.getId()) + ".png";
          Log.e(TAG,url);

            Glide.with(itemView)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .fallback(R.drawable.ic_launcher_background)
                    .override(400,400)
                    .into(img_poke);

            card_poke_view.setCardBackgroundColor(itemView.getResources().getColor(R.color.colorGray));
        }
    }

     List<CardPoke> cardPokeList; // Cached copy of cards
    public CardListAdapter(ArrayList<CardPoke> cardPokeList) {
        this.cardPokeList = cardPokeList;
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,null,false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.assignData(cardPokeList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.cardPokeList.size();
    }


}
