package com.lcwd.user.service.controllers;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class USerController {

    @Autowired
    private  UserService userService;

    @PostMapping
    public ResponseEntity<User> creatUser(@RequestBody User user){
        User user1=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);

    }
    // Single user get

    @GetMapping("/{userId}")
    public ResponseEntity<User>getSingleUser(@PathVariable("userId") String userId){
        User user= userService.getUser(userId);
        return ResponseEntity.ok(user);

    }
    //ALL USER GET

    @GetMapping
    public ResponseEntity<List<User>>getAllUsers(){
        List<User> allUser= userService.getAllUser();

        return ResponseEntity.ok(allUser);
    }

}


