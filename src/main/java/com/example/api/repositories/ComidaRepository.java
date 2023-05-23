package com.example.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.models.ComidaModel;

@Repository
public interface ComidaRepository extends JpaRepository<ComidaModel, UUID> {
}
