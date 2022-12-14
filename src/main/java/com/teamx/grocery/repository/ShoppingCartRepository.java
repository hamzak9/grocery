package com.teamx.grocery.repository;

import com.mongodb.BasicDBObject;
import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.ShoppingCart;
import com.teamx.grocery.model.User;
import org.bson.BsonDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart,String> {

    @Query("{ 'email' : ?0}")
    Optional<ShoppingCart> getCartByUsername(String email);

}
