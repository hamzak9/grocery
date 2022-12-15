package com.teamx.grocery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Document(collection = "ShoppingCart")
public class ShoppingCart {

    @Id
    private String id;

    public ShoppingCart(String email, HashMap<String, Integer> map) {
        this.email = email;
        this.map = map;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Field
    private String email;
    @Field
    HashMap<String,Integer> map = new HashMap<>();
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }
}
