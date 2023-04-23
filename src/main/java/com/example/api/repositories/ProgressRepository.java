package com.example.api.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.api.dto.ProgressDto;
import com.example.api.models.ProgressModel;

@Repository
public interface ProgressRepository {
    List<ProgressModel> getAll();

    ProgressModel getOne(String uuid);

    void post(ProgressDto progressDto);

    void put(ProgressDto progressDto, String uuid);

    void delete(String uuid);
}