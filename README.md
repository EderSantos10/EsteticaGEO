# 💅 EstéticaDaGEO

Sistema web para gestão de uma clínica de estética, desenvolvido com Java 21 e Spring Boot.  
Permite cadastro de clientes, login com senha, agendamento de serviços estéticos e controle via interface web ou API REST.

---

## 📌 Funcionalidades

- Cadastro de clientes com validação de CPF e telefone
- Criação de login com senha (somente números, 8 dígitos)
- Autenticação simples via tela web
- Agendamento de serviços (cílios, sobrancelhas, maquiagem)
- Verificação de conflito de horários
- Interface amigável com páginas HTML e estilo personalizado
- API REST para testes com Postman ou integração futura
- Segurança básica e separação entre perfis (admin, cliente — em estudo)

---

## 🛠️ Tecnologias utilizadas

| Tecnologia      | Função                                |
|------------------|----------------------------------------|
| Java 21          | Linguagem principal                    |
| Spring Boot 3.2  | Framework principal (API + MVC)        |
| Spring Web       | Criação de endpoints HTTP              |
| Spring Data JPA  | Integração com banco relacional        |
| PostgreSQL       | Banco de dados (usado via Railway)     |
| Lombok           | Redução de código boilerplate          |
| Thymeleaf        | Template engine para páginas HTML      |
| Gradle           | Gerenciador de build                   |
| Hibernate        | ORM utilizado pelo JPA                 |

---

## 📁 Estrutura de pacotes


com.esteticadageo
├── controller.api → Endpoints REST (JSON)
├── controller.web → Telas e formulários HTML
├── model → Entidades do banco de dados
├── repository → Interfaces JPA
├── service → Regras de negócio
├── dto → Objetos de entrada/saída (DTOs)
├── config → Configurações globais (ex: exceções)

yaml

---

## ⚙️ Como executar localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/seuusuario/esteticadageo.git
   cd esteticadageo
Configure o arquivo application.yml em src/main/resources/:

yaml
Copiar
Editar
spring:
  datasource:
    url: jdbc:postgresql://<SEU_HOST>:<PORTA>/<DB>
    username: <SEU_USUARIO>
    password: <SENHA>
Compile e execute:

./gradlew bootRun
Acesse:

arduino
Copiar
Editar
http://localhost:8080
📬 Endpoints REST disponíveis (via Postman)
POST /api/clientes

POST /api/profissionais   ← apenas via Postman (sem página web)

POST /api/logins/{clienteId}

POST /api/agendamentos

POST /api/logins/autenticar?login=xxx&senha=xxx

🖥️ Interface Web disponível
Página	URL
Página inicial	/
Cadastro de cliente	/web/clientes/novo
Login	/web/logins/login
Criar login/senha	/web/logins/novo
Agendamento de serviço	/web/agendamentos/novo

🔒 Segurança
Este projeto é para fins educacionais. Algumas simplificações foram feitas:

🔧 Criação de Profissional
Atenção: a criação de profissionais está disponível somente via API REST (Postman).
Por uma decisão de arquitetura e segurança, apenas perfis administradores poderiam realizar esse cadastro.
Como este projeto está em fase de estudos, a interface web para esse recurso ainda não foi implementada.

As senhas estão em texto puro (por enquanto)

Não há autenticação baseada em sessão ou token

Não há controle de acesso por perfil (ainda)

⚠️ Nunca use esse modelo diretamente em produção sem aplicar segurança adequada!

🙌 Contribuição (opcional)
Contribuições são bem-vindas! Sinta-se à vontade para abrir um issue ou forkar o projeto para melhorias.

👨‍💻 Autor
Desenvolvido por Eder Santos – estudante de Análise e Desenvolvimento de Sistemas.
