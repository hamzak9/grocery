package com.teamx.grocery.services;

import com.teamx.grocery.model.Orders;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.OrdersRepository;
import com.teamx.grocery.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    private ShoppingCartRepository repository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private OrdersRepository ordersRepository;



    public List<Orders> getAllOrders(){
        return ordersRepository.findAll();
    }


}
