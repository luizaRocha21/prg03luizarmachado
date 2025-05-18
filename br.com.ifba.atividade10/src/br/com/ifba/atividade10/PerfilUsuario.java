/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10;

/**
 *
 * @author luiza
 */
import java.util.ArrayList;
import java.util.List;

// Classe que representa um perfil de usuário com permissões de acesso
public class PerfilUsuario {
    private Long id;                // Identificador único do perfil
    private String descricao;       // Nome/descrição do perfil
    private List<String> permissoes; // Lista de permissões/autorizações

    // Construtor que inicializa o perfil com ID e descrição
    public PerfilUsuario(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.permissoes = new ArrayList<>(); // Inicializa lista vazia
    }

    // Métodos de acesso básicos (getters/setters)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public List<String> getPermissoes() { return permissoes; }

    // Métodos específicos para gerenciar permissões
    public void adicionarPermissao(String permissao) {
        this.permissoes.add(permissao); // Adiciona nova permissão
    }

    public void removerPermissao(String permissao) {
        this.permissoes.remove(permissao); // Remove permissão existente
    }

    // Representação textual do objeto para logs/depuração
    @Override
    public String toString() {
        return "PerfilUsuario{" +
               "id=" + id + 
               ", descricao='" + descricao + "'" + 
               ", permissoes=" + permissoes + "}";
    }
}