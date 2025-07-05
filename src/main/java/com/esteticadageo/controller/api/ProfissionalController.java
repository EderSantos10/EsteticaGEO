package com.esteticadageo.controller.api;

import com.esteticadageo.dto.ProfissionalDTO;
import com.esteticadageo.model.Profissional;
import com.esteticadageo.service.ProfissionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profissionais")
@RequiredArgsConstructor
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Profissional criarProfissional(@Valid @RequestBody ProfissionalDTO dto) {
        Profissional profissional = Profissional.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .nomeSocial(dto.getNomeSocial())
                .build();

        return profissionalService.salvar(profissional);
    }
}
