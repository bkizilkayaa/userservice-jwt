package com.example.burak.userservicejwt.service;

import com.example.burak.userservicejwt.model.Role;
import com.example.burak.userservicejwt.model.User;
import com.example.burak.userservicejwt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository,
                       RoleService roleService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user= userRepository.findByUsername(username);
       if(user==null){
            log.error("user not found by name : {}",username);
            throw new UsernameNotFoundException("user not found by id : "+username);
       }
       else{
            log.info("getting user by id : {} ",username);
       }
        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
        user.getRoles().forEach(
                role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));}
        );
        return new org.springframework.security
                      .core.userdetails.User(
                              user.getUsername(),user.getPassword(),authorities);
    }
    public User saveUser(User user){
        log.info("Saving new user {} to the database",user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
