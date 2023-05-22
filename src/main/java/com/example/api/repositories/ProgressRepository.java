package com.example.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.api.models.ProgressModel;

@Repository
public interface ProgressRepository extends JpaRepository<ProgressModel, UUID>{
    @Query(value = "SELECT BIN_TO_UUID(progress_id) progress_id, nome, ano, mes, dia, hora, minuto, segundo FROM progress ORDER BY nome ASC", nativeQuery = true)
    List<ProgressModel> findAllOrderBy();
}