package com.educational.grs_api.v1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "salas")
public class Sala {
    // identificação de objeto no banco de dados.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // campo de validação de quantidade de pessoas que utilizarão a sala. pode ser uma regra de não-criação
    //(caso a solicitação de pessoas na sala seja maior)
    @NotNull
    @Positive(message = "capacidade nao pode ser negativa")
    private int capacidade;

    //validação de estado do objeto. pode servir para uso.
    @Enumerated(EnumType.STRING)
    private StatusSala status;

    public Sala(int capacidade){
        this.capacidade = capacidade;
        this.status = StatusSala.ATIVA;
    }

    public void desativarSala(){
        this.status = StatusSala.INATIVA;
    }

    public void alteraCapacidadeSala(int novaCapacidade){
        this.capacidade = novaCapacidade;
    }

    public StatusSala getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public int getCapacidade() {
        return capacidade;
    }
}
