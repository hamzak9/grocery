package com.teamx.grocery.controller;

import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.Orders;
import com.teamx.grocery.model.ShoppingCart;
import com.teamx.grocery.model.User;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.OrdersRepository;
import com.teamx.grocery.repository.ShoppingCartRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.*;


@CrossOrigin
@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody String payload) throws NoSuchAlgorithmException {

        JSONObject json = new JSONObject(payload);
        System.out.println(payload);

        String fname = json.getString("fname");
        String lname = json.getString("lname");
        String city = json.getString("city");
        String province = json.getString("province");
        String address = json.getString("address");
        String email = json.getString("email");

        Optional<ShoppingCart> cart  = shoppingCartRepository.getCartByUsername(email);

        LinkedHashMap<String,Integer> freqMap = cart.get().getCart();
        Map<Item,Integer> map = new LinkedHashMap<>();

        List<Item> result = itemRepository.findAllById(cart.get().getCart().keySet());
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

            jsonArray.put(inner);

        }
        Orders order = new Orders(jsonArray,fname,lname,city,province,email,"500",address);

        ordersRepository.insert(order);
        shoppingCartRepository.delete(cart.get());

        return new ResponseEntity<>(HttpStatus.OK);



    }
}