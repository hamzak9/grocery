package com.teamx.grocery.controller;

import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.Orders;
import com.teamx.grocery.model.ShoppingCart;
import com.teamx.grocery.model.User;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.OrdersRepository;
import com.teamx.grocery.repository.ShoppingCartRepository;
import com.teamx.grocery.services.CartService;
import com.teamx.grocery.services.ItemService;
import com.teamx.grocery.services.OrdersService;
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
    private OrdersService ordersService;

    @Autowired
    CartService cartService;

    @Autowired
    ItemService itemService;

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody String payload) throws NoSuchAlgorithmException {
        JSONObject json = new JSONObject(payload);

        String fname = json.getString("fname");
        String lname = json.getString("lname");
        String city = json.getString("city");
        String province = json.getString("province");
        String address = json.getString("address");
        String email = json.getString("email");

        Optional<ShoppingCart> cart = cartService.getCartByUsername(email);

        LinkedHashMap<String, Integer> freqMap = cart.get().getCart();

        Map<Item, Integer> map = new LinkedHashMap<>();

        List<Item> result = itemService.findAllItemsById(cart.get().getCart().keySet());
        for (Item i : result) {
            map.put(i, freqMap.get(i.getId()));
        }
        List<LinkedHashMap<String, Object>> itemDetailsList = new ArrayList<>();
        double orderTotal = 0;
        for (Map.Entry<Item, Integer> entry : map.entrySet()) {
            LinkedHashMap<String, Object> itemDetails = new LinkedHashMap<>();

            itemDetails.put("name", entry.getKey().getProductName());
            itemDetails.put("price", Double.parseDouble(entry.getKey().getPrice()));
            itemDetails.put("count", entry.getValue());
            double itemTotal = Double.parseDouble(entry.getKey().getPrice()) * entry.getValue();

            itemDetails.put("orderTotal", itemTotal);
            orderTotal += itemTotal;
            itemDetailsList.add(itemDetails);
        }

        Orders order = new Orders(itemDetailsList, fname, lname, city, province, email, String.valueOf(orderTotal), address);

        ordersService.addOrder(order);
        cartService.removeCart(cart.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}