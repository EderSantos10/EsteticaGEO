package com.esteticadageo.service;

import com.esteticadageo.model.*;
import com.esteticadageo.repository.AgendamentoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    @Transactional
    public Agendamento salvar(@Valid Agendamento agendamento) {
        // valor inicial da entrada
        BigDecimal taxaAgendamento = BigDecimal.valueOf(50);

        // calcular o valor total dos serviços
        BigDecimal totalServicos = agendamento.getServicos().stream()
                .map(servico -> switch (servico) {
                    case CILIOS -> BigDecimal.valueOf(150);
                    case SOMBRANCELHAS -> BigDecimal.valueOf(100);
                    case MAQUIAGEM -> BigDecimal.valueOf(200);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        agendamento.setValorTotal(totalServicos.subtract(taxaAgendamento));
        agendamento.setDataCriacao(LocalDateTime.now());
        agendamento.setStatus(StatusAgendamento.AGENDADO);
        agendamento.setReembolsado(false);

        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarPorCliente(Cliente cliente) {
        return agendamentoRepository.findByCliente(cliente);
    }

    public boolean existeConflito(Profissional profissional, LocalDateTime dataHora) {
        return agendamentoRepository.existsByProfissionalAndDataHoraAndStatus(
                profissional, dataHora, StatusAgendamento.AGENDADO
        );
    }

    public List<Agendamento> listarPorProfissionalEPeriodo(Profissional profissional, LocalDateTime inicio, LocalDateTime fim) {
        return agendamentoRepository.findByProfissionalAndDataHoraBetween(profissional, inicio, fim);
    }
}
