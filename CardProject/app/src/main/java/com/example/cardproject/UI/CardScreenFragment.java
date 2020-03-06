package com.example.cardproject.UI;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardproject.Adapter.CardListAdapter;
import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.Entity.Deck;
import com.example.cardproject.Interactor.CardPokeInteractor;
import com.example.cardproject.Interface.CardScreenInterface;
import com.example.cardproject.Presenter.CardScreenPresenter;
import com.example.cardproject.R;
import com.example.cardproject.Utils.Constants;
import com.example.cardproject.ViewModel.DeckViewModel;

import java.util.ArrayList;
import java.util.List;

import static androidx.navigation.Navigation.findNavController;

public class CardScreenFragment extends Fragment implements  CardScreenInterface.View{

    static final String TAG = "CardScreenFragment";

    public CardScreenFragment newInstance() {
        return new CardScreenFragment();
    }

    CardScreenPresenter mPresenter;
    RecyclerView mRecyclerView;
    CardListAdapter mCardListAdapter;
    DeckViewModel deckViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView" );
        View rootView = inflater.inflate(R.layout.card_screen_fragment, container, false);
        setHasOptionsMenu(true); // Add this!
        mRecyclerView = rootView.findViewById(R.id.card_recycler_view);

        CardScreenFragmentArgs args = CardScreenFragmentArgs.fromBundle(this.getArguments());
        Button addCardsToDeck = rootView.findViewById(R.id.add_cards);
        Button deleteCardsFromDeck = rootView.findViewById(R.id.delete_cards);

        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int numeroColumnas = (int) (dpWidth / 180 );

        deckViewModel = new ViewModelProvider(this).get(DeckViewModel.class);
        mRecyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), numeroColumnas));
        mPresenter = createPresenter(getContext());


        deckViewModel.getDeck(args.getDeckID()).observe(this.getViewLifecycleOwner(), new Observer<Deck>() {
            @Override
            public void onChanged(Deck deck) {
                Log.e(TAG, "#####  onChanged ######");
                if(deck.getCardPokes() != null){
                    showCardData(deck.getCardPokes());
                }
            }
        });


        if(args.getVisualizationMode() == Constants.VIEW_DECK){
            addCardsToDeck.setVisibility(View.GONE);
            deleteCardsFromDeck.setVisibility(View.VISIBLE);
        }else{
            addCardsToDeck.setVisibility(View.VISIBLE);
            deleteCardsFromDeck.setVisibility(View.GONE);
            obtainCardDataFromApi();
        }


        addCardsToDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "addCardsToDeck");
                changeDeckCards(args.getDeckID());
            }
        });


        deleteCardsFromDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delete selected cards
                Log.e(TAG, "deleteCardsFromDeck");
                deleteCardsFromDeck(args.getDeckID());

            }
        });

        return rootView;
    }

    @Override
    public void obtainCardDataFromApi() {
        Log.e(TAG,"obtainCardData");
        mPresenter.fetchCardDataFromApi();
    }

    @Override
    public void showCardData(ArrayList<CardPoke> cardPokeData) {
        Log.e(TAG," lalalaal Showing card data");
        mCardListAdapter = new CardListAdapter(getContext(),cardPokeData, mPresenter);
        mRecyclerView.setAdapter(mCardListAdapter);
    }


    @Override
    public void changeDeckCards(int deckID) {
        ArrayList <CardPoke> cardsSelected = mCardListAdapter.getSelectedCards();
        for(CardPoke card: cardsSelected){
            card.setSelected(false);
        }
        mPresenter.updateDeckWhitCards(deckID,cardsSelected);
    }

    @Override
    public void backToDecksScreen() {
        findNavController(getView()).navigateUp();
    }


    protected CardScreenPresenter createPresenter(Context context) {
        return new CardScreenPresenter(this, new CardPokeInteractor());
    }

    public void deleteCardsFromDeck(int deckId){
        ArrayList <CardPoke> selectedCards = mCardListAdapter.getSelectedCards();
         mPresenter.deleteCardsFromDeck(deckId,selectedCards);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.card_search_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mCardListAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }
}


