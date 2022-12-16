package com.teamx.grocery.controller;

import com.mongodb.BasicDBObject;
import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.Orders;
import com.teamx.grocery.model.ShoppingCart;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.OrdersRepository;
import com.teamx.grocery.repository.ShoppingCartRepository;
import com.teamx.grocery.services.AdministratorService;
import com.teamx.grocery.services.ItemService;
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
    private AdministratorService administratorService;

    @GetMapping("/getAnalytics")
    public ResponseEntity<?> getAnalytics() {
        List<Orders> orders = administratorService.getAllOrders();

        List<Map<String, Object>> analyticsData = new ArrayList<>();
        double totalSales = 0;
        for (Orders order : orders) {
            List<LinkedHashMap<String, Object>> itemDetailsList = order.getItemDetailsList();
            double orderTotal = 0;
            for (LinkedHashMap<String, Object> singleMap : itemDetailsList) {
                orderTotal += (double) singleMap.get("orderTotal");
            }
            Map<String, Object> data = new HashMap<>();
            data.put("itemDetails", itemDetailsList);
            data.put("orderTotal", orderTotal);
            analyticsData.add(data);
            totalSales += orderTotal;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("analyticsData", analyticsData);
        response.put("totalSales", totalSales);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
