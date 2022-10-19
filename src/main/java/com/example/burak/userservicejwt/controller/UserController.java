package com.example.burak.userservicejwt.controller;

import com.example.burak.userservicejwt.model.User;
import com.example.burak.userservicejwt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), OK);
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username){
        return new ResponseEntity<>(userService.getUser(username),OK);
    }
    @PostMapping()
    public ResponseEntity<User> createNewUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user),CREATED);
    }
    @PostMapping("/addRoleToUser")
    public boolean addRoleToUser(@PathVariable)

}
