package com.example.burak.userservicejwt.service;

import com.example.burak.userservicejwt.model.Role;
import com.example.burak.userservicejwt.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.OK;

@Service
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<Role> saveRole(Role role){
        log.info("Saving new role {} to the database",role.getName());
        return new ResponseEntity<>(roleRepository.save(role), OK);
    }

    public Role getRole(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
