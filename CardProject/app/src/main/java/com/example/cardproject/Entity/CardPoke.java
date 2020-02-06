package com.example.cardproject.Entity;

import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

public class CardPoke {


    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String Name;

    @SerializedName("type")
    private String type;

    @SerializedName("url")
    private String url;

    @SerializedName("img")
    private ImageView img;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }
}
