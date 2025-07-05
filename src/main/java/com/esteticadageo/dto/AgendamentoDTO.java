package com.esteticadageo.dto;

import com.esteticadageo.model.ServicoTipo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AgendamentoDTO {

    @NotNull
    private Long clienteId;

    @NotNull
    private Long profissionalId;

    @NotNull
    private List<ServicoTipo> servicos;

    @NotNull
    private LocalDateTime dataHora;
}
