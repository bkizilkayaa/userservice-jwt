package com.example.burak.userservicejwt.repository;

import com.example.burak.userservicejwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
