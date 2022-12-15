package com.teamx.grocery.repository;

import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.ShoppingCart;
import com.teamx.grocery.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart,String> {

    @Query("{ 'username' : ?0}")
    Optional<HashMap<String,Integer>> getCartByUsername(String username);


}
