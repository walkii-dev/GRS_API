package com.educational.grs_api.v1.repository;

import com.educational.grs_api.v1.model.Reserva;
import com.educational.grs_api.v1.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva,Long> {


    List<Reserva> findBySala(Sala sala);
}
