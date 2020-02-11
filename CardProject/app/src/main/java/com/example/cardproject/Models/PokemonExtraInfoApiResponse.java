package com.example.cardproject.Models;

import java.util.ArrayList;

public class PokemonExtraInfoApiResponse {

    private ArrayList<String> abilities;
    private ArrayList<String> moves;
    private ArrayList<String> types;

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<String> moves) {
        this.moves = moves;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }
}
/*
"types":[
        {
        "slot":2,
        "type":{
        "name":"poison",
        "url":"https://pokeapi.co/api/v2/type/4/"
        }
        },
        {
        "slot":1,
        "type":{
        "name":"grass",
        "url":"https://pokeapi.co/api/v2/type/12/"
        }
        }
        ],*/
