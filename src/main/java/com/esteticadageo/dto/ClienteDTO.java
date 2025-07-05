package com.esteticadageo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteDTO {

    @NotNull
    @Size(max = 250)
    private String nome;

    @NotNull
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
    private String cpf;

    @NotNull
    @Pattern(regexp = "\\d{11}", message = "Telefone deve conter 11 dígitos")
    private String telefone;

    @Size(max = 250)
    private String endereco;
}
