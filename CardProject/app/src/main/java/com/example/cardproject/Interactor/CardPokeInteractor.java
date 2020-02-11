package com.example.cardproject.Interactor;

import android.util.Log;

import com.example.cardproject.Models.PokemonApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    final static int CARD_LIMIT = 500;

    public CardPokeInteractor() {
    }

    public interface onResultFetch{
       void  onSucces(PokemonApiResponse data);
       void  onFailure();
    }


    public void remoteFetch(onResultFetch listener) {
        Log.e(TAG,"remoteFetch");
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
        Call<PokemonApiResponse>call = pokeService.getPokeApiResponseWithParams(CARD_OFFSET,CARD_LIMIT);
        Log.e(TAG,"call");
        call.enqueue(new Callback<PokemonApiResponse>() {
            @Override
            public void onResponse(Call<PokemonApiResponse> call, Response<PokemonApiResponse> response) {
                Log.e(TAG,"onResponse");
                if(response.isSuccessful()) {
                   PokemonApiResponse apiResponse = response.body();
                    //ver si antes de mandar el on succes llamo a la api de info,
                    // ademas ver si conviene mandar directamente la CardList en vez del apiResponse

                    listener.onSucces(apiResponse);
                    Log.d("RETROFIT","" + apiResponse);
                } else {
                    Log.d("RETROFIT","error");
                }
            }

            @Override
            public void onFailure(Call<PokemonApiResponse> call, Throwable t) {
                Log.e(TAG,"onFailure");
                Log.d("RETROFIT","onFailure");
                Log.d("RETROFIT",t.getMessage());
                listener.onFailure();
            }
        });
    }


    public void getExtraInfoApi(onResultFetch listener) {

    }

    }
