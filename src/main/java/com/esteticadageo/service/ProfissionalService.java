package com.esteticadageo.service;

import com.esteticadageo.model.Profissional;
import com.esteticadageo.repository.ProfissionalRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    @Transactional
    public Profissional salvar(@Valid Profissional profissional) {
        return profissionalRepository.save(profissional);
    }

    public Optional<Profissional> buscarPorCpf(String cpf) {
        return profissionalRepository.findByCpf(cpf);
    }

    public Optional<Profissional> buscarPorId(Long id) {
        return profissionalRepository.findById(id);
    }

}
