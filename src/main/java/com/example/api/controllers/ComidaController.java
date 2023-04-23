package com.example.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.ComidaDto;
import com.example.api.repositories.ComidaRepository;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/comida")
public class ComidaController {

    @Autowired
    private ComidaRepository comidaRepository;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(comidaRepository.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "uuid")String uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(comidaRepository.getOne(uuid));
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody @Valid ComidaDto comidaDto) {
        try {
            comidaRepository.post(comidaDto.formattingToPost());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Added");
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Object> put(@RequestBody ComidaDto comidaDto, @PathVariable(value = "uuid")String uuid) {
        comidaRepository.put(comidaDto.formattingToPut(), uuid);
        return ResponseEntity.status(HttpStatus.OK).body("Updated");
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Object> delete(@PathVariable(value = "uuid")String uuid) {
        comidaRepository.delete(uuid);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }
}