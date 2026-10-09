package com.educational.grs_api.v1.service;

import com.educational.grs_api.v1.config.SobreposicaoException;
import com.educational.grs_api.v1.dto.CriacaoReservaDTO;
import com.educational.grs_api.v1.model.Reserva;
import com.educational.grs_api.v1.repository.ReservaRepository;
import com.educational.grs_api.v1.repository.SalaRepository;
import com.educational.grs_api.v1.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalaRepository salaRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaService(ReservaRepository reservaRepository,
                          SalaRepository salaRepository,
                          UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.salaRepository = salaRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public void criarReserva(CriacaoReservaDTO dadosCriacao) {

        var user = this.usuarioRepository.getReferenceById(dadosCriacao.usuarioId()); // se não tiver lança entityNotFoundException

        var sala = this.salaRepository.getReferenceById(dadosCriacao.salaId());

        if (dadosCriacao.dataFim().isBefore(dadosCriacao.dataInicio())) {
            throw new IllegalArgumentException(" fim não pode ser antes do começo.");
        }

        if (reservaRepository.existeConflitoDeHorario(
                dadosCriacao.salaId(),
                dadosCriacao.dataInicio(),
                dadosCriacao.dataFim())) {
            throw new SobreposicaoException(" estas datas estão indisponíveis para reserva. tente novamente.");
        }

        Reserva reserva = new Reserva(user, sala, dadosCriacao.dataInicio(), dadosCriacao.dataFim());

        reservaRepository.save(reserva);

    }
}
