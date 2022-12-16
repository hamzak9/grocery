package com.teamx.grocery.controller;

import com.mongodb.BasicDBObject;
import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.ShoppingCart;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.ShoppingCartRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.bson.BsonDocument;
import org.bson.json.JsonObject;
import org.json.JSONArray;
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
    @Autowired
    private ItemRepository itemRepository;


    @PostMapping("/getCart")
    public ResponseEntity<?> getCart(@RequestBody String payload){

        JSONObject json = new JSONObject(payload);
        String email = json.getString("email");

        Optional<ShoppingCart> cart = repository.getCartByUsername(email);

        if(!cart.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LinkedHashMap<String,Integer> freqMap = cart.get().getCart();
        Map<Item,Integer> map = new LinkedHashMap<>();

        List<Item> result = itemRepository.findAllById(cart.get().getCart().keySet());
        for(Item i : result){
            map.put(i,freqMap.get(i.getId()));
        }
        JSONArray jsonArray = new JSONArray();
//        int total = 0;
        for (Map.Entry<Item, Integer> entry : map.entrySet()) {
            JSONObject inner = new JSONObject();
            inner.put("name",entry.getKey().getProductName());
            inner.put("price",entry.getKey().getPrice());
            inner.put("count",entry.getValue().toString());
            inner.put("brand",entry.getKey().getBrand());

            jsonArray.put(inner);
//            total += inner.getInt("price") * inner.getInt("quantity");

        }
//
//        JSONObject cost = new JSONObject();
//        cost.put("total",String.valueOf(total));
//        jsonArray.put(cost);


        return new ResponseEntity<>(jsonArray.toString(),HttpStatus.OK);
    }

    @PostMapping("deleteItem")
    public ResponseEntity<?> deleteFromCart(@RequestBody String payload){
        JSONObject json = new JSONObject(payload);
        String email = json.getString("username");
        String itemName = json.getString("itemName");


       Optional<ShoppingCart> cart =  repository.getCartByUsername(email);
       Item item = itemRepository.findItemByName(itemName);

       cart.get().deleteFromCart(item.getId()); // delete the key value pair from map
        repository.save(cart.get());


        return new ResponseEntity<>(HttpStatus.OK);



    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody String payload){

        JSONObject json = new JSONObject(payload);
        String email = json.getString("username");
        String itemID = json.getString("item");

        Optional<ShoppingCart> userCart  = repository.getCartByUsername(email);


        if(!userCart.isPresent()){ // create new shopping cart
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
