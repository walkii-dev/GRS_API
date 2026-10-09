package com.educational.grs_api.v1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "salas")
public class Sala {
    // identificação de objeto no banco de dados.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //cria um número de identificação para a sala (em String para apenas diferenciar salas)
    @NotBlank(message = "A Sala precisa de um código de identificação.")
    @Size(max = 3)
    @Column(unique = true)
    private String codigo;


    // campo de validação de quantidade de pessoas que utilizarão a sala. pode ser uma regra de não-criação
    //(caso a solicitação de pessoas na sala seja maior)
    @NotNull
    @Positive(message = "capacidade nao pode ser negativa")
    private int capacidade;

    //validação de estado do objeto. pode servir para uso.
    @Enumerated(EnumType.STRING)
    private StatusSala status;

    public Sala(String codigo, int capacidade){

        if (capacidade < 1){
            throw new IllegalArgumentException("a sala deve ter uma capacidade positiva válida.");
        }

        // fazer validação de criação de sala

        this.codigo = codigo;
        this.capacidade = capacidade;
        this.status = StatusSala.ATIVA;
    }

    public Sala(){}

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

    public String getCodigo(){return codigo;}

    public int getCapacidade() {
        return capacidade;
    }
}
