package com.market.marketplace.controllers;

import com.market.marketplace.models.ERole;
import com.market.marketplace.models.MyUser;
import com.market.marketplace.models.Role;
import com.market.marketplace.services.MyUserDetailsService;
import com.market.marketplace.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/all")

    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<MyUser>> getAllUsers(){
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }

    @PutMapping("/update")

    @CrossOrigin(origins = "http://localhost4200")
    public ResponseEntity<?> updateUser(@RequestBody MyUser user){
//        System.out.println(user.getRoles());
        MyUser tempUser = userService.findUserByUsername(user.getUsername());
//        System.out.println(tempUser.getRoles());
        user.setRoles(tempUser.getRoles());
        MyUser updateUser = userService.updateUser(user);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{username}")
    public ResponseEntity<MyUser> getUserByUsername(@PathVariable("username") String username){
//        MyUser user = userDetailsService.loadUserByUsername(username);
        MyUser user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

}
