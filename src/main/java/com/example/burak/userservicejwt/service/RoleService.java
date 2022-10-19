package com.example.burak.userservicejwt.service;

import com.example.burak.userservicejwt.model.Role;
import com.example.burak.userservicejwt.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role){
        log.info("Saving new role {} to the database",role.getName());
        return roleRepository.save(role);
    }

    public Role getRole(String roleName) {
        return roleRepository.findByName(roleName);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
