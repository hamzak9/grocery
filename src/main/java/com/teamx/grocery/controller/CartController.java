package com.teamx.grocery.controller;

import com.mongodb.BasicDBObject;
import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.ShoppingCart;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.ShoppingCartRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.*;

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
        String email = json.getString("username");
        String itemID = json.getString("item");
        System.out.println(email);

        Optional<ShoppingCart> userCart  = repository.getCartByUsername(email);


        if(!userCart.isPresent()){ // create new shopping cart
            System.out.println("NO DOCUMENT HFOUND()");
            List<String> list = new ArrayList<>();
            list.add(itemID);
            LinkedHashMap<String,Integer> map = new LinkedHashMap<>();
            map.put(itemID,1);

            ShoppingCart cart = new ShoppingCart(email,map);
            repository.save(cart);
        }
        else{
            BasicDBObject obj = new BasicDBObject(userCart.get().getCart());
            LinkedHashMap<String,Integer> map =  (LinkedHashMap<String, Integer>) obj.toMap();

            map.put(itemID,map.getOrDefault(itemID,0)+1);
            userCart.get().setCart(map);

            repository.save(userCart.get());

        }


        return new ResponseEntity<>(HttpStatus.OK);


    }




}
