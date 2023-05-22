package com.example.api.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "progress")
public class ProgressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "progress_id")
    private UUID progress_id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable =  false)
    private int ano;
    @Column(nullable = false)
    private int mes;
    @Column(nullable = false)
    private int dia;
    @Column(nullable = false)
    private int hora;
    @Column(nullable = false)
    private int minuto;
    @Column(nullable = false)
    private int segundo;
}