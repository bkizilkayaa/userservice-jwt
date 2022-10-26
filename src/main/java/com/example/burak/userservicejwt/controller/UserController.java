package com.example.burak.userservicejwt.controller;

import com.example.burak.userservicejwt.dtos.RoleUserCreateRequest;
import com.example.burak.userservicejwt.model.User;
import com.example.burak.userservicejwt.service.UserService;
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
    @PostMapping("/save")
    public ResponseEntity<User> createNewUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user),CREATED);
    }
    //rollere göre authorization
    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleUserCreateRequest roleUserCreateRequest){
        userService.addRoleToUser(roleUserCreateRequest.getUserName(),
                roleUserCreateRequest.getRoleName());
        return ResponseEntity.ok().build();
    }


}
