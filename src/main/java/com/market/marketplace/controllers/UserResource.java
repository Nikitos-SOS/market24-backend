package com.market.marketplace.controllers;

import com.market.marketplace.models.ERole;
import com.market.marketplace.models.MyUser;
import com.market.marketplace.models.Role;
import com.market.marketplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<MyUser>> getAllUsers(){
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }
}
