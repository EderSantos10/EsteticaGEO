package com.esteticadageo.service;

import com.esteticadageo.model.Cliente;
import com.esteticadageo.repository.ClienteRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(@Valid Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public boolean cpfJaExiste(String cpf) {
        return clienteRepository.findByCpf(cpf).isPresent();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

}
