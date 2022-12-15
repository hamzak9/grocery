package com.teamx.grocery.repository;

import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item,String> {
//    List<Item> getAllItems();
}
