package com.esteticadageo.controller.web;

import com.esteticadageo.model.Cliente;
import com.esteticadageo.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/clientes")
@RequiredArgsConstructor
public class ClienteWebController {

    private final ClienteService clienteService;

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente-form";
    }

    @PostMapping
    public String salvar(@ModelAttribute @Valid Cliente cliente, Model model) {
        clienteService.salvar(cliente);
        model.addAttribute("mensagem", "Cliente cadastrado com sucesso!");

        // Redireciona para página de agendamento (por exemplo, /web/agendamentos/novo)
        return "redirect:/web/agendamentos/novo";
    }

}
