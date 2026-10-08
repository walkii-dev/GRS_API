package com.educational.grs_api.v1.controller;

import com.educational.grs_api.v1.dto.CriacaoReservaDTO;
import com.educational.grs_api.v1.model.Reserva;
import com.educational.grs_api.v1.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController (ReservaService reservaService){
        this.reservaService = reservaService;
    }

    public ResponseEntity<Reserva> criarReserva(@Valid CriacaoReservaDTO dados){
        this.reservaService.criarReserva(dados);
        return ResponseEntity.ok().build();
    }
}
