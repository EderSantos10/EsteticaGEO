package com.esteticadageo.controller.api;

import com.esteticadageo.dto.ClienteDTO;
import com.esteticadageo.model.Cliente;
import com.esteticadageo.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criarCliente(@Valid @RequestBody ClienteDTO dto) {
        if (clienteService.cpfJaExiste(dto.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        Cliente cliente = Cliente.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .endereco(dto.getEndereco())
                .build();

        return clienteService.salvar(cliente);
    }
}
