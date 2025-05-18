/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10;

/**
 *
 * @author luiza
 */
import java.time.LocalDateTime;
import java.util.UUID;

// Classe que representa um usuário do sistema com autenticação e controle de acesso
public class Usuario {
    private Long id;
    private PerfilUsuario perfil;
    private String nomeUsuario;
    private String email;
    private String senha;
    private LocalDateTime ultimoLogin;
    private Boolean ativo;

    // Construtor principal
    public Usuario(Long id, String nomeUsuario, String email, String senha, PerfilUsuario perfil) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.ultimoLogin = null;
        this.ativo = true;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public PerfilUsuario getPerfil() { return perfil; }
    public void setPerfil(PerfilUsuario perfil) { this.perfil = perfil; }
    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public LocalDateTime getUltimoLogin() { return ultimoLogin; }
    public void setUltimoLogin(LocalDateTime ultimoLogin) { this.ultimoLogin = ultimoLogin; }
    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    // Método de autenticação que retorna um log de auditoria
    public LogAuditoria logar(String senha, String ip) {
        if (this.senha.equals(senha) && this.ativo) {
            this.ultimoLogin = LocalDateTime.now();
            return new LogAuditoria(null, this, "Login realizado", LocalDateTime.now(), ip);
        }
        return new LogAuditoria(null, this, "Tentativa de login falhou", LocalDateTime.now(), ip);
    }

    // Cria uma nova sessão com token UUID aleatório
    public Sessao criarSessao() {
        return new Sessao(null, this, UUID.randomUUID().toString());
    }

    // Representação textual
    @Override
    public String toString() {
        return "Usuario{id=" + id + ", nomeUsuario='" + nomeUsuario + "', email='" + email + 
               "', ultimoLogin=" + ultimoLogin + "}";
    }
}