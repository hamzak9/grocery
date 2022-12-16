package com.teamx.grocery.controller;

import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.Promotion;
import com.teamx.grocery.services.ItemService;
import com.teamx.grocery.services.StoreService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/store")
public class StoreController {


    @Autowired
    private StoreService storeService;

    @PostMapping("/getPromotion")
    public ResponseEntity<?> getAllItems(@RequestBody String payload){
        JSONObject json = new JSONObject(payload);
        String userPromo = json.getString("promo");
        double discount = 0;
        List<Promotion> promos = storeService.getAllActivePromotions();
        for(Promotion p : promos){
    if(p.getPromotion().equals(userPromo)){
         discount = p.getDiscount();
    }
        }

        return new ResponseEntity<>(discount, HttpStatus.OK);

    }

}
