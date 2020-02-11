package com.example.cardproject.Interactor;

import android.util.Log;

import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.Models.PokemonApiResponse;
import com.example.cardproject.Models.PokemonExtraInfoApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CardPokeInteractor {

    final static String TAG = "CardPokeInteractor";
    final static String BASE_URL_POKE_INFO = "https://pokeapi.co/api/v2/";
    final static String BASE_URL_POKE_IMG = "https://pokeres.bastionbot.org/images/pokemon/"; //3.png {id}.png
    final static int CARD_OFFSET = 0;
    final static int CARD_LIMIT = 50;

    public CardPokeInteractor() {
    }

    public interface onResultFetch {
        void onSucces(PokemonApiResponse data);

        void onFailure();
    }


    public void remoteFetch(onResultFetch listener) {
        Log.e(TAG, "remoteFetch");
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_POKE_INFO)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        CardPokeService pokeService = retrofit.create(CardPokeService.class);
        Call<PokemonApiResponse> call = pokeService.getPokeApiResponseWithParams(CARD_OFFSET, CARD_LIMIT);
        Log.e(TAG, "call");
        call.enqueue(new Callback<PokemonApiResponse>() {
            @Override
            public void onResponse(Call<PokemonApiResponse> call, Response<PokemonApiResponse> response) {
                Log.e(TAG, "onResponse");
                if (response.isSuccessful()) {
                    PokemonApiResponse apiResponse = response.body();
                    ///////////////////////////////////////////////////////////////////////////////////////7
                    ArrayList<CardPoke> cardPokeData = apiResponse.getCardPokeList();
                    cardPokeData.forEach(cardPoke -> {
                        String url = cardPoke.getUrl();
                        int lastIndex = url.lastIndexOf("/");
                        int id = Integer.parseInt(url.substring(34, lastIndex));
                        cardPoke.setId(id);
                        getExtraInfoApi(listener, cardPoke);
                    });
                    ///////////////////////////////////////////////////////////////////////////////////////7

                    listener.onSucces(apiResponse);
                    Log.d("RETROFIT", "" + apiResponse);
                } else {
                    Log.d("RETROFIT", "error");
                }
            }

            @Override
            public void onFailure(Call<PokemonApiResponse> call, Throwable t) {
                Log.e(TAG, "onFailure");
                Log.d("RETROFIT", "onFailure");
                Log.d("RETROFIT", t.getMessage());
                listener.onFailure();
            }
        });
    }


    public void getExtraInfoApi(onResultFetch listener, CardPoke cardPoke) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_POKE_INFO +"pokemon/" + cardPoke.getId() + "/" )
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();


        CardPokeService pokeService = retrofit.create(CardPokeService.class);
        Call<PokemonExtraInfoApiResponse> call = pokeService.getPokeApiExtraInfoResponse();
        Log.e(TAG, "call");
        call.enqueue(new Callback<PokemonExtraInfoApiResponse>() {
            @Override
            public void onResponse(Call<PokemonExtraInfoApiResponse> call, Response<PokemonExtraInfoApiResponse> response) {
                Log.e(TAG, "onResponse");
                if (response.isSuccessful()) {
                    PokemonExtraInfoApiResponse pokemonExtraInfoApiResponse = response.body();
                  Log.e(TAG,"" + cardPoke.getName() + "es de tipo " + pokemonExtraInfoApiResponse.getTypes());

                } else {
                    Log.d("RETROFIT", "error");
                }
            }
            @Override
            public void onFailure(Call<PokemonExtraInfoApiResponse> call, Throwable t) {
                Log.e(TAG, "onFailure");
                listener.onFailure();
            }
        });

    }
}
