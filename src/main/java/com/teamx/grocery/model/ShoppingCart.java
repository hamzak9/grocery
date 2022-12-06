package com.teamx.grocery.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCart {

    List<Item> cart = new ArrayList<Item>();

    public HashMap<String,Integer> itemCount(ArrayList<Item> shoppingCart){
        HashMap<String,Integer> frequencyMap = new HashMap<>();

        for(Item item : shoppingCart){
            frequencyMap.put(item.getId(),frequencyMap.getOrDefault(item.getId(),0)+1);
        }

        return frequencyMap; // return how many of each item in cart
    }

    public void addItemToCart(Item item){
        cart.add(item); // Increase frequency instead? check if >0 and <0 to remove completely or not,
    }

}
