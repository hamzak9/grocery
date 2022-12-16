package com.teamx.grocery.services;
import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.User;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.List;
@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<Item> retrieveAllItems(){
        return repository.findAll();
    }
    public void updateItem(Item item){
        repository.save(item);
    }
    public Item findItemById(String itemID){
        return repository.findItemById(itemID);
    }
    public Item findItemByName(String name){
        return repository.findItemByName(name);

    }
    public List<Item> findAllItemsById(Collection<String> ids){
        return repository.findAllById(ids);
    }


}
