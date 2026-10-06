package com.educational.grs_api.v1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "usuarios")
public class Usuario {
    // identificação geral de objeto no banco de dados.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // campo que pode servir como visual numa possivel lista de usuários, por exemplo.
    @NotBlank(message = "nome do usuario nao pode estar em branco")
    private String nome;

    // possivel verificador unico de um objeto.
    @Email(message = "email precisa ser valido")
    private String email;

    public Usuario(String nome, String email){
        this.nome = nome;
        this.email = email;
    }
    public Usuario(){}

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
