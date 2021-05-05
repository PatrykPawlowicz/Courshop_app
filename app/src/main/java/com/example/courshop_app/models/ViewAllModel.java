package com.example.courshop_app.models;

import java.io.Serializable;

public class ViewAllModel implements Serializable {
    String name;
    String description;
    float rating;
    String rating_str;
    String img_url;
    String type;
    int price;

    public ViewAllModel() {
    }

    public ViewAllModel(String name, String description, float rating, String rating_str, String img_url, String type, int price) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.rating_str = rating_str;
        this.img_url = img_url;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getRating_str() {
        return rating_str;
    }

    public void setRating_str(String rating_str) {
        this.rating_str = rating_str;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
