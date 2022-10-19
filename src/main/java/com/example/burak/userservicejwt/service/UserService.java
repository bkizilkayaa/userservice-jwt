package com.example.burak.userservicejwt.service;

import com.example.burak.userservicejwt.model.Role;
import com.example.burak.userservicejwt.model.User;
import com.example.burak.userservicejwt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }
    public User saveUser(User user){
        log.info("Saving new user {} to the database",user.getName());
        return userRepository.save(user);
    }
    public List<User> getAllUsers(){
        log.info("Fetching all users...");
        return userRepository.findAll();
    }
    public void addRoleToUser(String userName, String roleName){
        log.info("Adding role {} to user {}",roleName,userName);
        User user = getUser(userName);
        Role role = roleService.getRole(roleName);
        user.getRoles().add(role);
    }
    public User getUser(String username){
        log.info("Fetching user {}",username);
        return userRepository.findByUsername(username);
    }
}
