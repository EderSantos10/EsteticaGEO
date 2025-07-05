package com.esteticadageo.controller.api;

import com.esteticadageo.dto.LoginDTO;
import com.esteticadageo.model.Cliente;
import com.esteticadageo.model.Login;
import com.esteticadageo.service.ClienteService;
import com.esteticadageo.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logins")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final ClienteService clienteService;

    @PostMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Login criarLogin(@PathVariable Long clienteId, @Valid @RequestBody LoginDTO dto) {
        if (loginService.loginJaExiste(dto.getLogin())) {
            throw new IllegalArgumentException("Login já existe.");
        }

        Cliente cliente = clienteService.buscarPorId(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Login login = Login.builder()
                .cliente(cliente)
                .login(dto.getLogin())
                .senha(dto.getSenha())
                .build();

        return loginService.salvar(login);
    }

    @PostMapping("/autenticar")
    public boolean autenticar(@RequestParam String login, @RequestParam String senha) {
        return loginService.autenticar(login, senha);
    }
}
