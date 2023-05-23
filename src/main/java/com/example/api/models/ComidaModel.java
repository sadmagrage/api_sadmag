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
@Table(name = "comida")
public class ComidaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comida_id")
    private UUID comida_id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private float carb;
    @Column(nullable = false)
    private float protl;
    @Column(nullable = false)
    private float proth;
    @Column(nullable = false)
    private float fat;
    @Column(nullable = false)
    private String img;    
}
