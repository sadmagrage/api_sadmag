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
    private Float ano;
    @NotNull
    private Float mes;
    @NotNull
    private Float dia;
    private Float hora;
    private Float minuto;
    private Float segundo;

    public ProgressDto passingDefaultValues() {
        if (this.getHora() == null) this.setHora(0.0f);
        if (this.getMinuto() == null) this.setMinuto(0.0f);
        if (this.getSegundo() == null) this.setSegundo(0.0f);

        return this;
    }
}
