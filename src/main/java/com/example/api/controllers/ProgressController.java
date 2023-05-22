package com.example.api.controllers;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.api.services.ProgressService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/progress")
public class ProgressController {
    
    @Autowired
    private ProgressService progressService;
    
    @GetMapping
    public ResponseEntity<Object> getAll() {
        return progressService.findAll();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "uuid") UUID uuid) {
        return progressService.findOne(uuid);
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody @Valid ProgressDto progressDto) {
        return progressService.saveProgress(progressDto);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Object> put(@RequestBody @Valid ProgressDto progressDto, @PathVariable(value = "uuid") UUID uuid) {
        return progressService.updateProgress(progressDto, uuid);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Object> delete(@PathVariable(value = "uuid") UUID uuid) {
        return progressService.delete(uuid);
    }
}
