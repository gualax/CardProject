package com.example.cardproject.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.Models.PokemonColor;
import com.example.cardproject.Presenter.CardScreenPresenter;
import com.example.cardproject.R;

import java.util.ArrayList;
import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> {

    static final String TAG = "CardListAdapter";
    static final String UrlImage = "https://pokeres.bastionbot.org/images/pokemon/";
    List<CardPoke> cardPokeList; // Cached copy of cards
    Context mContext;
    ArrayList<CardPoke> mSelectedCards = new ArrayList<>(); // Cached copy of cards
    CardScreenPresenter mCardScreenPresenter;


    class CardViewHolder extends RecyclerView.ViewHolder {
        TextView tv_poke_name, tv_nro_id,tv_poke_type;
        ImageView img_poke;
        CardView card_poke_view;
        ConstraintLayout frame_card;


        CardPoke cardPoke;
        int position;

        private CardViewHolder(View itemView) {
            super(itemView);
            tv_poke_name = itemView.findViewById(R.id.tv_poke_name);
            img_poke = itemView.findViewById(R.id.img_poke);
            card_poke_view = itemView.findViewById(R.id.card_poke_view);
            tv_nro_id = itemView.findViewById(R.id.tv_nro_id);
            frame_card = itemView.findViewById(R.id.frame_card);
            tv_poke_type = itemView.findViewById(R.id.tv_poke_type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "clickin card" + cardPoke.getName());

                    if (!cardPoke.getSelected()) {
                        cardPoke.setSelected(true);
                        mSelectedCards.add(cardPoke);
                    } else {
                        cardPoke.setSelected(false);
                        mSelectedCards.remove(cardPoke);
                    }
                    notifyItemChanged(position);
                }
            });
        }


        public void assignData(final CardPoke cardPoke, int position) {
            tv_poke_name.setText(cardPoke.getName());
            tv_nro_id.setText("N°:" + String.valueOf(cardPoke.getId()));
            String url = UrlImage + String.valueOf(cardPoke.getId()) + ".png";
            Log.e(TAG, url);

            Glide.with(itemView)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .fallback(R.drawable.ic_launcher_background)
                    .override(400, 400)
                    .into(img_poke);

            this.cardPoke = cardPoke;
            this.position = position;
        }

        public void obtainExtraInfoPokemon(CardPoke cardPoke){
                     mCardScreenPresenter.getExtraInfoPokes(cardPoke, new CardScreenPresenter.onResultFetchExtra() {
                         @Override
                         public void onSucces(CardPoke newCardPoke) {
                             Log.e(TAG, "obtainExtraInfoPokemon " + cardPoke.getName() +" es de type: " + cardPoke.getType());
                             tv_poke_type.setText(cardPoke.getType());
                             if(cardPoke.getSelected()){
                                 card_poke_view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorSelected));
                             }else {
                                 card_poke_view.setBackgroundColor(PokemonColor.getColorResource(cardPoke.getType(), mContext));
                             }
                         }

                         @Override
                         public void onFailure() {

                         }
                     });
        }


    }

    public CardListAdapter(Context context, ArrayList<CardPoke> cardPokeList, CardScreenPresenter cardScreenPresenter) {
        this.cardPokeList = cardPokeList;
        this.mContext = context;
        this.mCardScreenPresenter = cardScreenPresenter;
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, null, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.assignData(cardPokeList.get(position), position);
        holder.obtainExtraInfoPokemon(cardPokeList.get(position));
        if (cardPokeList.get(position).getSelected()) {
            holder.card_poke_view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorSelected));
        } else {
            holder.card_poke_view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorGray));
        }
    }

    @Override
    public int getItemCount() {
        if(cardPokeList == null){
            return 0;
        }else {
            return cardPokeList.size();
        }
    }

    public ArrayList<CardPoke> getSelectedCards() {
        return mSelectedCards;
    }

}
