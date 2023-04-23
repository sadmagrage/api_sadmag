package com.example.api.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.api.dto.ComidaDto;
import com.example.api.models.ComidaModel;

@Repository
public interface ComidaRepository {

    List<ComidaModel> getAll();

    ComidaModel getOne(String uuid);

    void post(ComidaDto comidaDto);
    
    void put(ComidaDto comidaDto, String uuid);

    void delete(String uuid);
}
