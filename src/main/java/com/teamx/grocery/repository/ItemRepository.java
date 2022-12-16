package com.teamx.grocery.repository;

import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item,String> {
    Item findItemById(String id);
    @Query("{ 'productName' : ?0}")
    Item findItemByName(String productName);

//    List<Item> getAllItems();
}
