package com.market.marketplace.service;

import com.market.marketplace.exception.UserNotFoundException;
import com.market.marketplace.model.User;
import com.market.marketplace.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;
    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public User addUser(User user){
        return userRepo.save(user);
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    public User updateUser(User user){
        return userRepo.save(user);
    }



    public User findUserById(Long id){
        return userRepo.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteUser(Long id){
        userRepo.deleteUserById(id);
    }

    public User login(String login, String password){
        List<User> users = userRepo.findAll();
        for (User user:users){
            if(user.getUserName().equals(login) && user.getPassword().equals(password)){
                return user;
            }
        }
        return new User();
    }

}
