package com.educational.grs_api.v1.repository;

import com.educational.grs_api.v1.model.Reserva;
import com.educational.grs_api.v1.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long> {

    @Query("""
        SELECT COUNT(r) > 0 
        FROM Reserva r 
        WHERE r.status = ATIVA
          AND r.sala.id = :salaId 
          AND r.fim > :novoInicio   
          AND r.inicio < :novoFim   
    """)
    boolean existeConflitoDeHorario(
            @Param("salaId") Long salaId,
            @Param("novoInicio") LocalDateTime novoInicio,
            @Param("novoFim") LocalDateTime novoFim
    );
}
