package com.example.api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    
    Optional<UserModel> findByUsername(String username);
}
