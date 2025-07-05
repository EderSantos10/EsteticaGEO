package com.esteticadageo.controller.web;

import com.esteticadageo.dto.LoginForm;
import com.esteticadageo.model.Cliente;
import com.esteticadageo.model.Login;
import com.esteticadageo.service.ClienteService;
import com.esteticadageo.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/logins")
@RequiredArgsConstructor
public class LoginWebController {

    private final LoginService loginService;
    private final ClienteService clienteService;

    // Exibe o formulário de registro de login
    @GetMapping("/novo")
    public String showRegistrationForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login-registration";
    }

    // Processa o registro de login
    @PostMapping
    public String registerLogin(
            @ModelAttribute @Valid LoginForm form,
            Model model
    ) {
        // Verifica confirmação de senha
        if (!form.getSenha().equals(form.getConfirmarSenha())) {
            model.addAttribute("erro", "As senhas não coincidem.");
            return "login-registration";
        }

        if (loginService.loginJaExiste(form.getLogin())) {
            model.addAttribute("erro", "Login já existe.");
            return "login-registration";
        }

        Cliente cliente = clienteService.buscarPorId(form.getClienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Login login = Login.builder()
                .cliente(cliente)
                .login(form.getLogin())
                .senha(form.getSenha())
                .build();

        loginService.salvar(login);

        // Após registro, redireciona ao formulário de login
        return "redirect:/web/login";
    }

    // GET do formulário de autenticação (já existente)
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // POST de autenticação
    @PostMapping("/login")
    public String authenticate(
            @RequestParam String login,
            @RequestParam String senha,
            Model model
    ) {
        boolean ok = loginService.autenticar(login, senha);
        if (ok) {
            return "redirect:/web/agendamentos/novo";
        } else {
            model.addAttribute("erro", "Login ou senha inválidos.");
            return "login";
        }
    }
}
