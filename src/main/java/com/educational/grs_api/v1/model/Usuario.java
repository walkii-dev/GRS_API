package com.educational.grs_api.v1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //esta classe tem somente dois atributos simples para mostrar o usuario e email para ter identificacao unica

    @NotBlank(message = "nome do usuario nao pode estar em branco")
    private String nome;

    @Email(message = "email precisa ser valido")
    private String email;

    public Usuario(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
