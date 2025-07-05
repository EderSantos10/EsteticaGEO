package com.esteticadageo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {

    @NotNull
    @Size(max = 15)
    private String login;

    @NotNull
    @Pattern(regexp = "\\d{8}", message = "Senha deve conter 8 dígitos numéricos")
    private String senha;
}
