package com.teamx.grocery.controller;

import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.ShoppingCart;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.ShoppingCartRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ShoppingCartRepository repository;



    @GetMapping("/getCart")
    public ResponseEntity<?> getCart(@RequestBody String payload){
        JSONObject json = new JSONObject(payload);
        String username = json.getString("username");

//       HashMap<Item,Integer> map  = repository.getCartByUsername(username);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody String payload){
        System.out.println(payload);

        JSONObject json = new JSONObject(payload);
        String username = json.getString("username");
        String itemID = json.getString("item");

        Optional<HashMap<String,Integer>> map  = repository.getCartByUsername(username);
        if(!map.isPresent()){ // create new shopping cart
            HashMap<String,Integer> itemMap = new HashMap<>();
            itemMap.put(itemID,1);
            ShoppingCart cart = new ShoppingCart(username,itemMap);
            repository.save(cart);
        }
        else{
            HashMap<String,Integer> itemMap = map.get();
            if (itemMap.containsKey(itemID)) {
                itemMap.put(itemID, itemMap.get(itemID) + 1);
            } else {
                itemMap.put(itemID, 1);
            }
            ShoppingCart cart = new ShoppingCart(username,itemMap);
            repository.save(cart);

        }

        return new ResponseEntity<>(HttpStatus.OK);


    }




}
