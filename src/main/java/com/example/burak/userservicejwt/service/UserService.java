package com.example.burak.userservicejwt.service;

import com.example.burak.userservicejwt.model.Role;
import com.example.burak.userservicejwt.model.User;
import com.example.burak.userservicejwt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
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
