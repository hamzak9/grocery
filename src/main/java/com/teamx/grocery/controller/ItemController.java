package com.teamx.grocery.controller;

import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.User;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping("/addToCart")
    public ResponseEntity<?> handleRegistrationForm(HttpServletRequest request) throws NoSuchAlgorithmException {

        return null;
    }

    @GetMapping("/getAllItems")
    public ResponseEntity<?> getAllItems(){
        List<Item> items = repository.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);

    }

}
