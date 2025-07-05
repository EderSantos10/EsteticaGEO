package com.esteticadageo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 250)
    private String nome;

    @NotNull
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
    @Column(length = 11, unique = true)
    private String cpf;

    @NotNull
    @Pattern(regexp = "\\d{11}", message = "Telefone deve conter exatamente 11 dígitos numéricos")
    @Column(length = 11)
    private String telefone;

    @Size(max = 50)
    @Column(name = "nome_social")
    private String nomeSocial;
}
