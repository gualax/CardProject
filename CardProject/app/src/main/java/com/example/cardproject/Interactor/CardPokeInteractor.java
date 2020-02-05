package com.example.cardproject.Interactor;

import android.util.Log;

import com.example.cardproject.Entity.Card;
import com.example.cardproject.Entity.Pokemon;
import com.example.cardproject.Models.PokemonApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CardPokeInteractor implements Callback<PokemonApiResponse> {

    final static String BASE_URL_POKE_INFO = "https://pokeapi.co/api/v2/pokemon/";
    final static String BASE_URL_POKE_IMG = "https://pokeres.bastionbot.org/images/pokemon/"; //3.png {id}.png




    interface onResultFetch{
       void  onSucces(PokemonApiResponse data);
       void  onFailure();
    }


    public void remoteFetch() {

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

        Call<PokemonApiResponse>call = pokeService.getPokeApiResponse();
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<PokemonApiResponse> call, Response<PokemonApiResponse> response) {
        if(response.isSuccessful()) {
            PokemonApiResponse apiResponse = response.body();
            Log.d("RETROFIT","" + apiResponse);

        } else {
            Log.d("RETROFIT","error");
        }
    }

    @Override
    public void onFailure(Call<PokemonApiResponse> call, Throwable t) {
        Log.d("RETROFIT","onFailure");
        Log.d("RETROFIT",t.getMessage());


    }

}
