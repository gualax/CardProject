package com.example.cardproject.Interactor;

import com.example.cardproject.Entity.CardPoke;
import com.example.cardproject.Models.PokemonApiResponse;
import com.example.cardproject.Models.PokemonExtraInfoApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface CardPokeService {


@GET("pokemon/")
Call<PokemonApiResponse> getPokeApiResponse();


@GET(".")
Call<PokemonExtraInfoApiResponse> getPokeApiExtraInfoResponse();



@GET("pokemon/") //?offset=20&limit=20"
Call<PokemonApiResponse> getPokeApiResponseWithParams(@Query("offset") int offset, @Query("limit") int limit);
  //Call<PokemonApiResponse> getPokeApiResponse(@Query("api_key") String API_KEY);

@GET("pokemon/") //?offset=20&limit=20"
Call<CardPoke> getCardPokeInfo(@Query("offset") int offset, @Query("limit") int limit);


}