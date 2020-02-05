package com.example.cardproject.Interactor;

import com.example.cardproject.Models.PokemonApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

interface CardPokeService {

  //https://pokeapi.co/api/v2/pokemon/?offset=20&limit=20"

@GET(".")
Call<PokemonApiResponse> getPokeApiResponse();



@GET(".")
Call<PokemonApiResponse> getPokeApiImgResponse();
  //Call<PokemonApiResponse> getPokeApiResponse(@Query("api_key") String API_KEY);

}