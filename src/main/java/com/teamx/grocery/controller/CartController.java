package com.teamx.grocery.controller;

import com.mongodb.BasicDBObject;
import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.ShoppingCart;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.ShoppingCartRepository;
import com.teamx.grocery.services.CartService;
import com.teamx.grocery.services.ItemService;
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
    public CartService cartService;
    @Autowired
    public ItemService itemService;

    @PostMapping("/getCart")
    public ResponseEntity<?> getCart(@RequestBody String payload){

        JSONObject json = new JSONObject(payload);
        String email = json.getString("email");

        Optional<ShoppingCart> cart = cartService.getCartByUsername(email);

        if(!cart.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LinkedHashMap<String,Integer> freqMap = cart.get().getCart();
        Map<Item,Integer> map = new LinkedHashMap<>();

        List<Item> result = cartService.findAllItemsById(cart.get().getCart().keySet());

        for(Item i : result){
            map.put(i,freqMap.get(i.getId()));
        }
        JSONArray jsonArray = new JSONArray();
        for (Map.Entry<Item, Integer> entry : map.entrySet()) {
            JSONObject inner = new JSONObject();
            inner.put("name",entry.getKey().getProductName());
            inner.put("price",entry.getKey().getPrice());
            inner.put("count",entry.getValue().toString());
            inner.put("brand",entry.getKey().getBrand());
            inner.put("id",entry.getKey().getId());

            jsonArray.put(inner);

        }


        return new ResponseEntity<>(jsonArray.toString(),HttpStatus.OK);
    }

    @PostMapping("deleteItem")
    public ResponseEntity<?> deleteFromCart(@RequestBody String payload){
        JSONObject json = new JSONObject(payload);
        String email = json.getString("username");
        String itemName = json.getString("itemName");


       Optional<ShoppingCart> cart =  cartService.getCartByUsername(email);
       Item item = itemService.findItemByName(itemName);

       cart.get().deleteFromCart(item.getId()); // delete item from cart
       cartService.updateCart(cart.get());


        return new ResponseEntity<>(HttpStatus.OK);


    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody String payload){

        JSONObject json = new JSONObject(payload);
        String email = json.getString("username");
        String itemID = json.getString("item");

        Optional<ShoppingCart> userCart  = cartService.getCartByUsername(email);


        if(!userCart.isPresent()){ // create new shopping cart
            List<String> list = new ArrayList<>();
            list.add(itemID);
            LinkedHashMap<String,Integer> map = new LinkedHashMap<>();
            map.put(itemID,1);

            ShoppingCart cart = new ShoppingCart(email,map);
            cartService.updateCart(cart);
        }
        else{
            BasicDBObject obj = new BasicDBObject(userCart.get().getCart());
            LinkedHashMap<String,Integer> map =  (LinkedHashMap<String, Integer>) obj.toMap();

            map.put(itemID,map.getOrDefault(itemID,0)+1);
            userCart.get().setCart(map);

            cartService.updateCart(userCart.get());

        }


        return new ResponseEntity<>(HttpStatus.OK);


    }




}
