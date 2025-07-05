package com.esteticadageo.repository;

import com.esteticadageo.model.Agendamento;
import com.esteticadageo.model.Cliente;
import com.esteticadageo.model.Profissional;
import com.esteticadageo.model.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByCliente(Cliente cliente);

    List<Agendamento> findByProfissionalAndDataHoraBetween(Profissional profissional, LocalDateTime inicio, LocalDateTime fim);

    boolean existsByProfissionalAndDataHoraAndStatus(Profissional profissional, LocalDateTime dataHora, StatusAgendamento status);
}
