package com.teamx.grocery.controller;

import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.User;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository repository;
    @GetMapping("/getAllItems")
    public ResponseEntity<?> getAllItems(){
        List<Item> items = repository.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);

    }

    @PostMapping("/addComment")
    public ResponseEntity<?> addComment(@RequestBody String payload){
        JSONObject json = new JSONObject(payload);
        // make sure that the payload contains these two informations, so we can
        // identify the correct item to add the comment to
        String comment = json.getString("comment");
        String itemID = json.getString("id");

        Item item = repository.findItemById(itemID);
       // item.setReviews();  --> create a method like appendComment that appends the comment
        // to the end of the item's reviews list ( Check item in Model folder)
        // then call repository.save(item) to update the item in mongo and ur done


        return new ResponseEntity<>(HttpStatus.OK);

    }

}
