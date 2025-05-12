/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author luiza
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma conta bancária
 * - Gera números de conta automaticamente
 * - Controla operações básicas (depósito, saque, etc.)
 */
public class Conta {
    private static int contadorContas = 1000; // Contador para gerar números únicos
    private final int numero;
    private final String tipo; // "cc" (corrente) ou "cp" (poupança)
    private final Cliente cliente;
    private double saldo;
    private boolean ativa;
    private final List<String> extrato;

    /**
     * Construtor privado (usar factory method abrirConta)
     */
    private Conta(Cliente cliente, String tipo) {
        this.numero = ++contadorContas;
        this.cliente = cliente;
        this.tipo = tipo.toLowerCase();
        this.saldo = 0;
        this.ativa = false;
        this.extrato = new ArrayList<>();
    }

    /**
     * Factory method para abertura de conta
     * @return Nova conta ou null se tipo inválido
     */
    public static Conta abrirConta(Cliente cliente, String tipo) {
        if (!tipo.equalsIgnoreCase("cc") && !tipo.equalsIgnoreCase("cp")) {
            return null;
        }
        
        Conta novaConta = new Conta(cliente, tipo);
        novaConta.ativa = true;
        
        // Crédito inicial conforme tipo
        double creditoInicial = tipo.equalsIgnoreCase("cc") ? 50.0 : 150.0;
        novaConta.depositar(creditoInicial, "Crédito inicial");
        
        return novaConta;
    }

    /**
     * Realiza depósito na conta
     * @param valor Valor a depositar (deve ser positivo)
     * @param descricao Descrição da operação
     * @return true se sucesso, false se falhou
     */
    public boolean depositar(double valor, String descricao) {
        if (!ativa || valor <= 0) return false;
        
        saldo += valor;
        registrarOperacao("DEPÓSITO", valor, descricao);
        return true;
    }

    /**
     * Realiza saque na conta
     * @param valor Valor a sacar (deve ser positivo e <= saldo)
     * @param descricao Descrição da operação
     * @return true se sucesso, false se falhou
     */
    public boolean sacar(double valor, String descricao) {
        if (!ativa || valor <= 0 || valor > saldo) return false;
        
        saldo -= valor;
        registrarOperacao("SAQUE", -valor, descricao);
        return true;
    }

    /**
     * Cobra mensalidade conforme tipo de conta
     */
    public boolean cobrarMensalidade() {
        double taxa = tipo.equals("cc") ? 12.0 : 20.0;
        return sacar(taxa, "Taxa mensal");
    }

    /**
     * Fecha a conta (apenas se saldo zero)
     */
    public boolean fecharConta() {
        if (saldo != 0) return false;
        ativa = false;
        registrarOperacao("CONTA FECHADA", 0, null);
        return true;
    }

    // Registra operação no extrato
    private void registrarOperacao(String tipo, double valor, String descricao) {
        String registro = String.format("[%s] %s: R$ %.2f - %s", 
            java.time.LocalDateTime.now(), 
            tipo, 
            valor, 
            descricao != null ? descricao : "");
        extrato.add(registro);
    }

    // Getters
    public int getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public Cliente getCliente() { return cliente; }
    public double getSaldo() { return saldo; }
    public boolean isAtiva() { return ativa; }
    public List<String> getExtrato() { return new ArrayList<>(extrato); }
    
    /**
     * Retorna descrição completa da conta
     */
    @Override
    public String toString() {
        return String.format(
            "Conta %d (%s)\nCliente: %s\nSaldo: R$ %.2f\nStatus: %s",
            numero, 
            tipo.equals("cc") ? "Corrente" : "Poupança",
            cliente.getNome(),
            saldo,
            ativa ? "Ativa" : "Inativa"
        );
    }
}