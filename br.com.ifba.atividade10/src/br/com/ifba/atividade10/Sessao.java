/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10;

/**
 *
 * @author luiza
 */
// Classe que representa uma sessão de usuário no sistema
public class Sessao {
    private Long id;         // ID único da sessão
    private Usuario usuario; // Usuário autenticado
    private String token;    // Token de segurança da sessão

    // Construtor que inicializa todos os atributos
    public Sessao(Long id, Usuario usuario, String token) {
        this.id = id;
        this.usuario = usuario;
        this.token = token;
    }

    // Getters e Setters padrão para acesso controlado
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    // Representação textual da sessão (para logs/debug)
    @Override
    public String toString() {
        return "Sessao{" +
               "id=" + id + 
               ", usuario=" + usuario.getNomeUsuario() + 
               ", token='" + token + "'}";
    }
}