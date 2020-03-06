package com.example.cardproject.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.example.cardproject.R;

public class PokemonByType {


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
    private static final String DARK = "dark";
    private static final String GHOST = "ghost";
    private static final String STEEL = "steel";
    private static final String ROCK = "rock";








    public static int getColorResource(String type, Context mContext) {

        switch (type) {

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

            case DARK:
                return ContextCompat.getColor(mContext, R.color.colorDark);

            case GHOST:
                return ContextCompat.getColor(mContext, R.color.colorGhost);

            case STEEL:
                return ContextCompat.getColor(mContext, R.color.colorSteel);

            case ROCK:
                return ContextCompat.getColor(mContext, R.color.colorGround);

            default:
                return ContextCompat.getColor(mContext, R.color.colorNormal);
        }
    }



        public static Drawable getIconResource(String type, Context mContext){

            switch (type){

                case POISON:
                    return ContextCompat.getDrawable(mContext,R.drawable.poison_element_icon);

                case FLYING:
                    return ContextCompat.getDrawable(mContext,R.drawable.flying_element_icon);

                case DRAGON:
                    return ContextCompat.getDrawable(mContext,R.drawable.dragon_element_icon);

                case FIRE:
                    return ContextCompat.getDrawable(mContext,R.drawable.fire_element_icon);

                case WATER:
                    return ContextCompat.getDrawable(mContext,R.drawable.water_element_icon);

                case GRASS:
                    return ContextCompat.getDrawable(mContext,R.drawable.grass_element_icon);

                case BUG:
                    return ContextCompat.getDrawable(mContext,R.drawable.bug_element_icon);

                case NORMAL:
                    return ContextCompat.getDrawable(mContext,R.drawable.normal_element_icon);

                case ELECTRIC:
                    return ContextCompat.getDrawable(mContext,R.drawable.electric_element_icon);

                case GROUND:
                    return ContextCompat.getDrawable(mContext,R.drawable.ground_element_icon);

                case FIGHTING:
                    return ContextCompat.getDrawable(mContext,R.drawable.fighting_element_icon);

                case FARY:
                    return ContextCompat.getDrawable(mContext,R.drawable.fary_element_icon);

                case PSYHIC:
                    return ContextCompat.getDrawable(mContext,R.drawable.psichic_element_icon);

                case ICE:
                    return ContextCompat.getDrawable(mContext,R.drawable.ice_element_icon);

                case DARK:
                    return ContextCompat.getDrawable(mContext,R.drawable.dark_element_icon);

                case GHOST:
                    return ContextCompat.getDrawable(mContext,R.drawable.ghost_element_icon);

                case STEEL:
                    return ContextCompat.getDrawable(mContext,R.drawable.steel_element_icon);

                case ROCK:
                    return ContextCompat.getDrawable(mContext,R.drawable.rock_element_icon);

                default:
                    return ContextCompat.getDrawable(mContext,R.drawable.normal_element_icon);
            }
  }








}
