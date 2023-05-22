package com.example.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgressDto {
    @NotBlank
    private String nome;
    @NotNull
    private int ano;
    @NotNull
    private int mes;
    @NotNull
    private int dia;
    private int hora;
    private int minuto;
    private int segundo;
}
