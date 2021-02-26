package com.future.hazelcastcaching.controller;

import com.future.hazelcastcaching.model.User;
import com.future.hazelcastcaching.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserManager userManager;

    @PostMapping("/user")
    public ResponseEntity<User> saveUser (@RequestBody User user) {
        User addedUser = userManager.save(user);
       return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public User findUserById (@PathVariable long id) {
        return userManager.findById(id);
    }

    @GetMapping("/user")
    public Map<Long, User> getUserListFromCache () {
        return userManager.getList();
    }
}
