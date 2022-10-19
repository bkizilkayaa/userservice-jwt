package com.example.burak.userservicejwt.repository;

import com.example.burak.userservicejwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
