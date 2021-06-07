package com.market.marketplace.services;

import com.market.marketplace.models.Seller;
import com.market.marketplace.repositories.SellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SellerService {
    @Autowired
    private SellerRepo sellerRepo;

    public Seller addSeller(Seller seller){
        return sellerRepo.save(seller);
    }

    public List<Seller> findAll(){
        return sellerRepo.findAll();
    }

    public Boolean existByName(String username){
        return sellerRepo.existsByUsername(username);
    }
}
