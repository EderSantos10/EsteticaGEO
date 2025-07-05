package com.esteticadageo.controller.web;

import com.esteticadageo.dto.AgendamentoDTO;
import com.esteticadageo.model.Agendamento;
import com.esteticadageo.model.Cliente;
import com.esteticadageo.model.Profissional;
import com.esteticadageo.model.ServicoTipo;
import com.esteticadageo.service.AgendamentoService;
import com.esteticadageo.service.ClienteService;
import com.esteticadageo.service.ProfissionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/agendamentos")
@RequiredArgsConstructor
public class AgendamentoWebController {

    private final AgendamentoService agendamentoService;
    private final ClienteService clienteService;
    private final ProfissionalService profissionalService;

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("agendamento", new AgendamentoDTO());
        return "agendamento-form";
    }

    @PostMapping
    public String salvar(@ModelAttribute @Valid AgendamentoDTO dto,
                         @RequestParam(required = false) List<String> servicos,
                         Model model) {
        try {
            Cliente cliente = clienteService.buscarPorId(dto.getClienteId())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

            Profissional profissional = profissionalService.buscarPorId(dto.getProfissionalId())
                    .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado"));

            dto.setServicos(
                    servicos != null
                            ? servicos.stream()
                            .map(s -> ServicoTipo.valueOf(s))
                            .toList()
                            : List.of()
            );


            if (agendamentoService.existeConflito(profissional, dto.getDataHora())) {
                model.addAttribute("erro", "Horário indisponível para esse profissional.");
                return "agendamento-form";
            }

            Agendamento agendamento = Agendamento.builder()
                    .cliente(cliente)
                    .profissional(profissional)
                    .servicos(dto.getServicos())
                    .dataHora(dto.getDataHora())
                    .build();

            agendamentoService.salvar(agendamento);

            model.addAttribute("mensagem", "Agendamento realizado com sucesso!");
            model.addAttribute("agendamento", new AgendamentoDTO());
            return "agendamento-form";

        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            return "agendamento-form";
        }
    }
}
