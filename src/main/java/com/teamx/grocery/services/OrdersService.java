package com.teamx.grocery.services;

import com.teamx.grocery.model.Orders;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.OrdersRepository;
import com.teamx.grocery.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ItemRepository itemRepository;


    public void addOrder(Orders o){
        ordersRepository.insert(o);
    }


}
