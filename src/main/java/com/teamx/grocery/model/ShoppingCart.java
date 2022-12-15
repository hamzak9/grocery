package com.teamx.grocery.model;

import com.mongodb.BasicDBObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Document(collection = "ShoppingCart")
public class ShoppingCart {

    @Id
    private String id;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LinkedHashMap<String, Integer> getCart() {
        return cart;
    }

    public void setCart(LinkedHashMap<String, Integer> cart) {
        this.cart = cart;
    }

    @Field
    private String email;

    public ShoppingCart(String email, LinkedHashMap<String, Integer> cart) {
        this.email = email;
        this.cart = cart;
    }

    @Field
    LinkedHashMap<String,Integer> cart;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}


