package com.educational.grs_api.v1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "salas")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive(message = "capacidade nao pode ser negativa")
    private int capacidade;

    public Sala(int capacidade){
        this.capacidade = capacidade;
    }

    public Long getId() {
        return id;
    }

    public int getCapacidade() {
        return capacidade;
    }
}
