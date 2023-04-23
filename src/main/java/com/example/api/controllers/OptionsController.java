package com.example.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.repositories.OptionsRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/options")
public class OptionsController {

    @Autowired
    private OptionsRepository optionsRepository;

    @GetMapping("/show_tables")
    public List<String> showTables() {
        return optionsRepository.showTables();
    }

    @GetMapping("/columns/{table}")
    public ResponseEntity<Object> showColumns(@PathVariable(value = "table") String table) {

        return ResponseEntity.status(HttpStatus.OK).body(optionsRepository.showColumns(table));
    }
}
