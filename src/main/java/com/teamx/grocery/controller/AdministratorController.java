package com.teamx.grocery.controller;

import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.Orders;
import com.teamx.grocery.model.ShoppingCart;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.OrdersRepository;
import com.teamx.grocery.repository.ShoppingCartRepository;
import org.bson.BsonDocument;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.query.Query;

import static java.rmi.server.LogStream.log;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdministratorController {
    @Autowired
    private ShoppingCartRepository repository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private OrdersRepository ordersRepository;
    @GetMapping("/getAnalytics")
    public ResponseEntity<?> getAnalytics() {
        List<Orders> orders = ordersRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        List<JSONObject> items = new ArrayList<>();


        for (Orders order : orders) {
            try {
                JSONArray jsonArray = new JSONArray(mapper.writeValueAsString(order.getItemsJson()));
                for (int i = 0; i < jsonArray.length(); i++) {

                    items.add(jsonArray.getJSONObject(i));
                }
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (JSONException e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }


}
