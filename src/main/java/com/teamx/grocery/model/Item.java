package com.teamx.grocery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Item {

    @Id
    private String id;
    @Field
    private String name;
    @Field
    private String price;
    @Field
    private String rating;
    @Field
    private String brand;


    public Item(String name, String price, String rating,String brand) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.brand=brand;
    }


    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
