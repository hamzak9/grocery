package com.teamx.grocery.repository;

import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdersRepository extends MongoRepository<Orders,String> {

}