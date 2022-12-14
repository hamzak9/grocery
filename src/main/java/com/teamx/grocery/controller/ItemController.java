package com.teamx.grocery.controller;

import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.User;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.UserRepository;
import com.teamx.grocery.services.ItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/getAllItems")
    public ResponseEntity<?> getAllItems(){
        List<Item> items = itemService.retrieveAllItems();

        return new ResponseEntity<>(items, HttpStatus.OK);

    }

    @PostMapping("/addComment")
    public ResponseEntity<?> addComment(@RequestBody String payload){
        JSONObject json = new JSONObject(payload);
        String comment = json.getString("comment");
        String itemID = json.getString("id");

        Item item = itemService.findItemById(itemID);

        item.getReviews().add(comment);

        itemService.updateItem(item);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
