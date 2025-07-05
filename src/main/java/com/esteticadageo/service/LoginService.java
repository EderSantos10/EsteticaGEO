package com.esteticadageo.service;

import com.esteticadageo.model.Login;
import com.esteticadageo.repository.LoginRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    @Transactional
    public Login salvar(@Valid Login login) {
        return loginRepository.save(login);
    }

    public boolean loginJaExiste(String login) {
        return loginRepository.findByLogin(login).isPresent();
    }

    public boolean autenticar(String login, String senha) {
        return loginRepository.findByLogin(login)
                .map(user -> user.getSenha().equals(senha))
                .orElse(false);
    }
}
