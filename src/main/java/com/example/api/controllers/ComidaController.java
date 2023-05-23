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

import com.example.api.dto.ComidaDto;
import com.example.api.services.ComidaService;

import jakarta.validation.Valid;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/comida")
public class ComidaController {

    @Autowired
    ComidaService comidaService;

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return comidaService.findAll();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "uuid")UUID uuid) {
        return comidaService.findOne(uuid);
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody @Valid ComidaDto comidaDto) {
        return comidaService.saveComida(comidaDto);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Object> put(@RequestBody ComidaDto comidaDto, @PathVariable(value = "uuid") UUID uuid) {
        return comidaService.updateComida(comidaDto, uuid);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Object> delete(@PathVariable(value = "uuid") UUID uuid) {
        return comidaService.deleteComida(uuid);
    }
}
