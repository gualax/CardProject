package com.example.cardproject.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cardproject.Entity.Deck;
import com.example.cardproject.Presenter.DeckScreenPresenter;
import com.example.cardproject.R;
import com.example.cardproject.UI.DeckScreenFragmentDirections;
import com.example.cardproject.Utils.Constants;
import com.example.cardproject.ViewModel.DeckViewModel;

import java.util.List;

import static androidx.navigation.Navigation.findNavController;

public class DeckListAdapter extends RecyclerView.Adapter<DeckListAdapter.DeckViewHolder> {

    static final String TAG = "DeckListAdapter";
    List<Deck> deckList;
    private LayoutInflater mInflater = null;
    DeckScreenPresenter mDeckScreenPresenter;
    Context mContext;


    class DeckViewHolder extends RecyclerView.ViewHolder {
        TextView tv_deck_name;
        Button btn_add_card, btn_see_deck;
        ImageButton btn_deck_menu;
        Deck deckSelected;


        private DeckViewHolder(View itemView) {
            super(itemView);
            tv_deck_name = itemView.findViewById(R.id.tv_deck_name);
            btn_add_card = itemView.findViewById(R.id.btn_add_to_deck);
            btn_see_deck = itemView.findViewById(R.id.btn_see_deck);
            btn_deck_menu = itemView.findViewById(R.id.btn_deck_menu);

            btn_add_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    findNavController(v).navigate(DeckScreenFragmentDirections.toCardScreen(deckSelected.getId(), Constants.ADD_TO_DECK));
                }
            });

            btn_see_deck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Deck ID: " + deckSelected.getId());
                    findNavController(v).navigate(DeckScreenFragmentDirections.toCardScreen(deckSelected.getId(), Constants.VIEW_DECK));
                }
            });

            btn_deck_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //creating a popup menu
                    PopupMenu popup = new PopupMenu(itemView.getContext(), btn_deck_menu);
                    //inflating menu from xml resource
                    popup.inflate(R.menu.deck_options_menu);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_add:
                                    findNavController(v).navigate(DeckScreenFragmentDirections.toCardScreen(deckSelected.getId(), Constants.ADD_TO_DECK));
                                    return true;
                                case R.id.menu_edit:
                                    findNavController(v).navigate(DeckScreenFragmentDirections.toCardScreen(deckSelected.getId(), Constants.VIEW_DECK));
                                    return true;
                                case R.id.menu_delete:
                                    mDeckScreenPresenter.deleteDeck(deckSelected.getId());
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                    //displaying the popup
                    popup.show();
                }
            });


        }

        public void assignData(final Deck deck){
            tv_deck_name.setText(deck.getName());
            this.deckSelected = deck;

        }

    }

    public DeckListAdapter(List<Deck> deckList) {
        this.deckList = deckList;
    }

    public  DeckListAdapter(Context context, DeckScreenPresenter deckScreenPresenter){
        mInflater = LayoutInflater.from(context);
        mContext = context;
        mDeckScreenPresenter = deckScreenPresenter;
    }


    @NonNull
    @Override
    public DeckListAdapter.DeckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.from(parent.getContext()).inflate(R.layout.deck_item,parent,false);
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
