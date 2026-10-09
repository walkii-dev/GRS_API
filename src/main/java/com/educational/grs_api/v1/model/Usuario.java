package com.educational.grs_api.v1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    // identificação geral de objeto no banco de dados.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // campo que pode servir como visual numa possivel lista de usuários, por exemplo.
    @NotBlank(message = "apelido do usuario nao pode estar em branco")
    @Column(unique = true)
    private String apelido;

    public Usuario(String apelido){
        this.apelido = apelido;
    }
    public Usuario(){}

    public Long getId() {
        return id;
    }

    public String getApelido(){ return apelido;}


}
