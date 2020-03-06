package com.example.cardproject.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.Utils.PokemonByType;
import com.example.cardproject.Presenter.CardScreenPresenter;
import com.example.cardproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.CardViewHolder> implements Filterable {

    static final String TAG = "CardListAdapter";
    static final String UrlImage = "https://pokeres.bastionbot.org/images/pokemon/";
    List<CardPoke> cardPokeList; // Cached copy of cards
    Context mContext;
    ArrayList<CardPoke> mSelectedCards = new ArrayList<>(); // Cached copy of cards
    CardScreenPresenter mCardScreenPresenter;
    private List<CardPoke> fullCardList;


    class CardViewHolder extends RecyclerView.ViewHolder {
        TextView tv_poke_name, tv_nro_id,tv_poke_type;
        ImageView img_poke, img_element_icon;
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
            img_element_icon = itemView.findViewById(R.id.img_element_icon);


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
            tv_poke_name.setText(capitalize(cardPoke.getName()));
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
                     tv_poke_type.setText(capitalize(cardPoke.getType()));
                     img_element_icon.setImageDrawable(PokemonByType.getIconResource(cardPoke.getType(),mContext));

                     if(cardPoke.getSelected()){
                         card_poke_view.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorSelected));
                     }else {
                         card_poke_view.setBackgroundColor(PokemonByType.getColorResource(cardPoke.getType(), mContext));
                     }
                 }

                 @Override
                 public void onFailure() {

                 }
          });
        }


    }

    public CardListAdapter(Context context, ArrayList<CardPoke> cardPokeList, CardScreenPresenter cardScreenPresenter) {
        Log.e(TAG, "CREATE ADAPTER");
        this.cardPokeList = cardPokeList;
        this.mContext = context;
        this.mCardScreenPresenter = cardScreenPresenter;
        fullCardList = new ArrayList<>(cardPokeList);  // for filter and search bar
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, null, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder");

        // fullCardList = new ArrayList<>(cardPokeList);  // for filter and search bar
        holder.assignData(cardPokeList.get(position), position);
        holder.obtainExtraInfoPokemon(cardPokeList.get(position));

    }

    @Override
    public int getItemCount() {
        if(cardPokeList == null){
            return 0;
        }else {
            return cardPokeList.size();
        }
    }

    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }

    public ArrayList<CardPoke> getSelectedCards() {
        Log.e(TAG, "getSelectedCards");
        return mSelectedCards;
    }



    /// para realizar el filtrado
    @Override
    public Filter getFilter() {
        return cardPokeFilter;
    }

    private Filter cardPokeFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CardPoke> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(fullCardList);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(CardPoke item : fullCardList){
                    if(item.getName().toLowerCase().contains(filterPattern)){    ///o startWith
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            cardPokeList.clear();
            cardPokeList.addAll((List)results.values);
            notifyDataSetChanged();

        }
    };




}
