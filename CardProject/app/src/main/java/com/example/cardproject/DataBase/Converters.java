package com.example.cardproject.DataBase;

import androidx.room.TypeConverter;

import com.example.cardproject.Entity.CardPoke;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class Converters {


    @TypeConverter
    public ArrayList<CardPoke> getObjectFromString(String jsonString){
        Type listType = new TypeToken<ArrayList<CardPoke>>(){}.getType();
        ArrayList<CardPoke> list = new Gson().fromJson(jsonString, listType);
        return list;
    }


    @TypeConverter
    public String stringFromObject(ArrayList<CardPoke> list){
        Gson gson = new Gson();
        String jsonString = gson.toJson(list);
        return jsonString;

    }

}