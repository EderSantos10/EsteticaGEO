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
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false, unique = true)
    private Cliente cliente;

    @NotNull
    @Size(max = 15)
    @Column(unique = true)
    private String login;

    @NotNull
    @Pattern(regexp = "\\d{8}", message = "Senha deve conter exatamente 8 dígitos numéricos")
    private String senha;
}
