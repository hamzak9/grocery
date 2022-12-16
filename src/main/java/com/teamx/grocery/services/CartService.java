package com.teamx.grocery.services;

import com.teamx.grocery.model.Item;
import com.teamx.grocery.model.ShoppingCart;
import com.teamx.grocery.repository.ItemRepository;
import com.teamx.grocery.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private ShoppingCartRepository repository;
    @Autowired
    private ItemRepository itemRepository;

    public Optional<ShoppingCart> getCartByUsername(String username){
        return repository.getCartByUsername(username);
    }
    public List<Item> findAllItemsById(Collection<String> ids){
        return itemRepository.findAllById(ids);

    }
    public void removeCart(ShoppingCart cart){
        repository.delete(cart);

    }

    public void updateCart(ShoppingCart cart){
        repository.save(cart);
    }


}
