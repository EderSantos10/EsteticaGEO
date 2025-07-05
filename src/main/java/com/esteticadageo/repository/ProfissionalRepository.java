package com.esteticadageo.repository;

import com.esteticadageo.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    Optional<Profissional> findByCpf(String cpf);
}
