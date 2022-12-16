package com.teamx.grocery.repository;

import com.teamx.grocery.model.Orders;
import com.teamx.grocery.model.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PromotionRepository extends MongoRepository<Promotion,String> {

}