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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.R;

import java.util.ArrayList;
import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> {

    static final String TAG = "CardListAdapter";
    static final String UrlImage = "https://pokeres.bastionbot.org/images/pokemon/";
    class CardViewHolder extends RecyclerView.ViewHolder {
        TextView tv_poke_name;
        ImageView img_poke;
        CardView card_poke_view;

        private CardViewHolder(View itemView) {
            super(itemView);
            tv_poke_name = itemView.findViewById(R.id.tv_poke_name);
            img_poke = itemView.findViewById(R.id.img_poke);
            card_poke_view = itemView.findViewById(R.id.card_poke_view);
        }

        public void assignData(final CardPoke cardPoke){
          tv_poke_name.setText(cardPoke.getName());
          String url =  UrlImage + String.valueOf(cardPoke.getId())+".png";
          Log.e(TAG,url);

            Glide.with(itemView)  //2
                    .load(url) //3
                    .centerCrop() //4
                    .placeholder(R.drawable.ic_launcher_foreground) //5
                    .error(R.drawable.ic_launcher_foreground) //6
                    .fallback(R.drawable.ic_launcher_background) //7
                    .override(400,400)
                    .into(img_poke); //
            card_poke_view.setCardBackgroundColor(itemView.getResources().getColor(R.color.colorGray));
        }
    }

    //private final LayoutInflater mInflater;
     List<CardPoke> cardPokeList; // Cached copy of cards

    //public CardListAdapter(Context context) { mInflater = LayoutInflater.from(context); }
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
