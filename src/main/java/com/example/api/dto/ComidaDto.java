package com.example.api.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComidaDto {
    @NotBlank
    private String nome;
    @NotNull
    private Integer quantidade;
    private Float carb;
    private Float protl;
    private Float proth;
    private Float fat;
    private String img;
    
    public ComidaDto formattingToPost() {
        this.setCarb((this.getCarb() != null) ? this.getCarb()/this.getQuantidade() : 0.0f);
        this.setProtl((this.getProtl() != null) ? this.getProtl()/this.getQuantidade() : 0.0f);
        this.setProth((this.getProth() != null) ? this.getProth()/this.getQuantidade() : 0.0f);
        this.setFat((this.getFat() != null) ? this.getFat()/this.getQuantidade() : 0.0f);

        return this;
    }

    public ComidaDto formattingToPut() {
        if (this.getCarb() != null) this.setCarb(this.getCarb()/this.getQuantidade());
        if (this.getProtl() != null) this.setProtl(this.getProtl()/this.getQuantidade());
        if (this.getProth() != null) this.setProth(this.getProth()/this.getQuantidade());
        if (this.getFat() != null) this.setFat(this.getFat()/this.getQuantidade());

        return this;
    }
}