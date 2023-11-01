package com.project.user.service.controllers;

import com.project.user.service.entities.User;
import com.project.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    // creating fallback method for circuitbreaker"

    public ResponseEntity<User> ratingHotelFallback (String userId, Exception ex){
        logger.info("Fallback is executed because service is down: ", ex.getMessage());
        User user= User.builder().
                email("dummy@gmail.com").
                name("dummy").
                about("dummy user created ").
                userId("1453415").
                build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList= userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }



}
