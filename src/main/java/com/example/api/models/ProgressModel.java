package com.example.api.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressModel {
    private UUID progress_id;
    private String nome;
    private int ano;
    private int mes;
    private int dia;
    private int hora;
    private int minuto;
    private int segundo;
}