package com.example.api.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComidaModel {
    private UUID comida_id;
    private String nome;
    private float carb;
    private float protl;
    private float proth;
    private float fat;
    private String img;
}
