package com.teamx.grocery.repository;

import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item,String> {

}
