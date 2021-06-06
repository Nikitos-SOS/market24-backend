package com.market.marketplace.services;


import com.market.marketplace.models.MyUser;
import com.market.marketplace.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    private UserRepo userRepo;
    UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public MyUser addUser(MyUser user){
        return userRepo.save(user);
    }

    public List<MyUser> findAll(){
        return userRepo.findAll();
    }

    public Boolean existByName(String username){
        return userRepo.existsByUsername(username);
    }

}
