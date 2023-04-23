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

import com.example.api.dto.ProgressDto;
import com.example.api.repositories.ProgressRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/progress")
public class ProgressController {
    
    @Autowired
    private ProgressRepository progressRepository;
    
    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(progressRepository.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "uuid") String uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(progressRepository.getOne(uuid));
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody @Valid ProgressDto progressDto) {
        try {
            progressRepository.post(progressDto);   
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error");
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Object> put(@RequestBody ProgressDto progressDto, @PathVariable(value = "uuid") String uuid) {
        System.out.println(progressDto);
        progressRepository.put(progressDto, uuid);

        return ResponseEntity.status(HttpStatus.OK).body("Updated");
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Object> delete(@PathVariable(value = "uuid") String uuid) {
        progressRepository.delete(uuid);

        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }
}
