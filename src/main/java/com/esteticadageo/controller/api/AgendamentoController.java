package com.esteticadageo.controller.api;

import com.esteticadageo.dto.AgendamentoDTO;
import com.esteticadageo.model.Agendamento;
import com.esteticadageo.model.Cliente;
import com.esteticadageo.model.Profissional;
import com.esteticadageo.service.AgendamentoService;
import com.esteticadageo.service.ClienteService;
import com.esteticadageo.service.ProfissionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final ClienteService clienteService;
    private final ProfissionalService profissionalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agendamento agendar(@Valid @RequestBody AgendamentoDTO dto) {
        Cliente cliente = clienteService.buscarPorId(dto.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Profissional profissional = profissionalService.buscarPorId(dto.getProfissionalId())
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));

        if (agendamentoService.existeConflito(profissional, dto.getDataHora())) {
            throw new IllegalArgumentException("Horário indisponível para esse profissional.");
        }

        Agendamento agendamento = Agendamento.builder()
                .cliente(cliente)
                .profissional(profissional)
                .dataHora(dto.getDataHora())
                .servicos(dto.getServicos())
                .build();

        return agendamentoService.salvar(agendamento);
    }
}
