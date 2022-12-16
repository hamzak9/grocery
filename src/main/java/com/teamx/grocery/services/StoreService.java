package com.teamx.grocery.services;

import com.teamx.grocery.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teamx.grocery.model.Promotion;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private PromotionRepository promotionRepository;
    public List<Promotion> getAllActivePromotions(){
            return promotionRepository.findAll();

    }

}
