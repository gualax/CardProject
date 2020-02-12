package com.example.cardproject.Models;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.cardproject.R;

public class PokemonColor {


    private static final String POISON = "poison";
    private static final String FLYING = "flying";
    private static final String DRAGON = "dragon";
    private static final String FIRE = "fire";
    private static final String WATER = "water";
    private static final String GRASS = "grass";
    private static final String BUG = "bug";
    private static final String NORMAL = "normal";
    private static final String ELECTRIC  = "electric";
    private static final String GROUND = "ground";
    private static final String FIGHTING = "fighting";
    private static final String FARY = "fary";
    private static final String PSYHIC = "psychic";
    private static final String ICE = "ice";



  public static int getColorResource(String type, Context mContext){

     switch (type){

         case POISON:
             return ContextCompat.getColor(mContext, R.color.colorPoison);

         case FLYING:
             return ContextCompat.getColor(mContext, R.color.colorFlying);

         case DRAGON:
             return ContextCompat.getColor(mContext, R.color.colorDragon);

         case FIRE:
             return ContextCompat.getColor(mContext, R.color.colorFire);

         case WATER:
             return ContextCompat.getColor(mContext, R.color.colorWater);

         case GRASS:
             return ContextCompat.getColor(mContext, R.color.colorGrass);

         case BUG:
             return ContextCompat.getColor(mContext, R.color.colorBug);

         case NORMAL:
             return ContextCompat.getColor(mContext, R.color.colorNormal);

         case ELECTRIC:
             return ContextCompat.getColor(mContext, R.color.colorElectric);

         case GROUND:
             return ContextCompat.getColor(mContext, R.color.colorGround);

         case FIGHTING:
             return ContextCompat.getColor(mContext, R.color.colorFigthing);

         case FARY:
             return ContextCompat.getColor(mContext, R.color.colorFairy);

         case PSYHIC:
             return ContextCompat.getColor(mContext, R.color.colorPsychic);

         case ICE:
             return ContextCompat.getColor(mContext, R.color.colorIce);

         default:
             return ContextCompat.getColor(mContext, R.color.colorNormal);
     }
  }








}
