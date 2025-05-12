/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luiza
 */
import java.util.Date; 
import java.text.SimpleDateFormat;

public class Cliente {
    // Atributos básicos do cliente
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private Date dataNascimento;
    
    // Construtor básico
    public Cliente(String nome, String cpf) {
        this.setNome(nome);
        this.setCpf(cpf);
    }
    
    // Construtor completo
    public Cliente(String nome, String cpf, String endereco, String telefone, 
                  String email, Date dataNascimento) {
        this(nome, cpf);
        this.setEndereco(endereco);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.setDataNascimento(dataNascimento);
    }
    
    // Método de validação de CPF (melhorado)
    public static boolean validarCPF(String cpf) {
        if (cpf == null) return false;
        
        cpf = cpf.replaceAll("[^0-9]", "");
        
        // Verifica se tem 11 dígitos ou se é uma sequência de números iguais
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        
        try {
            // Cálculo do primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int resto = soma % 11;
            int digito1 = (resto < 2) ? 0 : 11 - resto;
            
            // Cálculo do segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            resto = soma % 11;
            int digito2 = (resto < 2) ? 0 : 11 - resto;
            
            // Verifica se os dígitos calculados conferem com os informados
            return (Character.getNumericValue(cpf.charAt(9)) == digito1 && 
                    Character.getNumericValue(cpf.charAt(10)) == digito2);
        } catch (Exception e) {
            return false;
        }
    }
    
    // Getters e Setters com validações
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome.trim();
        } else {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (validarCPF(cpf)) {
            this.cpf = cpf.replaceAll("[^0-9]", "");
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        // Validação simples de telefone
        if (telefone == null || telefone.replaceAll("[^0-9]", "").length() < 8) {
            throw new IllegalArgumentException("Telefone inválido");
        }
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Validação simples de e-mail
        if (email != null && email.contains("@") && email.contains(".") && email.length() > 5) {
            this.email = email.toLowerCase();
        } else {
            throw new IllegalArgumentException("E-mail inválido");
        }
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        // Verifica se a data é no passado
        if (dataNascimento != null && dataNascimento.before(new Date())) {
            this.dataNascimento = dataNascimento;
        } else {
            throw new IllegalArgumentException("Data de nascimento inválida");
        }
    }
    
    // Métodos auxiliares
    public String getDataNascimentoFormatada() {
        if (dataNascimento == null) return "Não informada";
        return new SimpleDateFormat("dd/MM/yyyy").format(dataNascimento);
    }
    
    public String formatarCPF() {
        if (cpf == null || cpf.length() != 11) return cpf;
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + 
               cpf.substring(6, 9) + "-" + cpf.substring(9);
    }
    
    @Override
    public String toString() {
        return nome + " (CPF: " + formatarCPF() + ")";
    }
    
    // Método para exibir informações completas
    public String exibirInformacoesCompletas() {
        return String.format(
            "Nome: %s\nCPF: %s\nEndereço: %s\nTelefone: %s\nE-mail: %s\nData Nasc.: %s",
            nome,
            formatarCPF(),
            endereco != null ? endereco : "Não informado",
            telefone != null ? telefone : "Não informado",
            email != null ? email : "Não informado",
            getDataNascimentoFormatada()
        );
    }
}