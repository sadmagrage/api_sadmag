package com.example.api.services;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.api.dto.ComidaDto;
import com.example.api.models.ComidaModel;
import com.example.api.repositories.ComidaRepository;

import jakarta.transaction.Transactional;

@Service
public class ComidaService {

    @Autowired
    ComidaRepository comidaRepository;

    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(comidaRepository.findAll());
    }

    public ResponseEntity<Object> findOne(UUID uuid) {
        var comidaModelOptional = comidaRepository.findById(uuid);

        if (!comidaModelOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comida not found.");
        
        return ResponseEntity.status(HttpStatus.OK).body(comidaModelOptional.get());
    }

    @Transactional
    public ResponseEntity<Object> saveComida(ComidaDto comidaDto) {
        var comidaModel = new ComidaModel();

        try {
            BeanUtils.copyProperties(comidaDto.formatting(), comidaModel);
            comidaRepository.save(comidaModel);

            return ResponseEntity.status(HttpStatus.CREATED).body("Comida created.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error creating comida.");        }
    }

    @Transactional
    public ResponseEntity<Object> updateComida(ComidaDto comidaDto, UUID uuid) {
        var comidaModel = new ComidaModel();
        var comidaModelOptional = comidaRepository.findById(uuid);

        if (!comidaModelOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comida not found.");

        try {
            BeanUtils.copyProperties(comidaDto.formatting(), comidaModel);
            comidaModel.setComida_id(uuid);
            
            comidaRepository.save(comidaModel);

            return ResponseEntity.status(HttpStatus.OK).body("Comida updated.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error updating comida.");
        }
    }

    @Transactional
    public ResponseEntity<Object> deleteComida(UUID uuid) {
        var comidaModelOptional = comidaRepository.findById(uuid);

        if (!comidaModelOptional.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comida not found.");

        comidaRepository.deleteById(uuid);
        return ResponseEntity.status(HttpStatus.OK).body("Comida deleted.");
    }
}
