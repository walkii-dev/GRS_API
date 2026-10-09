package com.educational.grs_api.v1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Table(name = "reservas")
public class Reserva {

    //identificador geral da entidade, vai ajudar muito nas consultas ao banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //requisito essencial da reserva
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "toda reserva precisa de um usuário. esta informação não pode ser nula.")
    private Usuario usuario;

    //requisito essencial da reserva
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "toda reserva precisa de uma sala. esta informação não pode ser nula.")
    private Sala sala;

    //definição de tempo onde a sala será usada pelo usuario.
    //pode servir de base para análise de conflito (através de Period)
    private LocalDateTime inicio;
    private LocalDateTime fim;

    //lidar com mudança de status de uma reserva, obtendo controle sobre o uso.
    @Enumerated(EnumType.STRING)
    private StatusReserva status;

    public Reserva(Usuario usuario, Sala sala, LocalDateTime inicio, LocalDateTime fim){

        if (fim.isBefore(inicio)){
            throw new IllegalArgumentException("data final não pode ser anterior ao início!");
        }

        this.usuario = usuario;
        this.sala = sala;
        this.inicio = inicio;
        this.fim = fim;
        this.status = StatusReserva.ATIVA;
    }

    public Reserva(){}

    public Long getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Sala getSala() {
        return sala;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public StatusReserva getStatus() {
        return status;
    }
}
