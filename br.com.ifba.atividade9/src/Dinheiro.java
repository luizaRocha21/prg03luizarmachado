/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luiza
 */
import javax.swing.JOptionPane;
public class Dinheiro implements Pagamento {
    private double valor;
    
    public Dinheiro(double valor) {
        this.valor = valor;
    }
    
    @Override
    public double calcularTotal() {
        return valor * 0.9; // 10% de desconto
    }
    
    @Override
    public void imprimirRecibo() {
        String recibo = "RECIBO - Pagamento em Dinheiro\n" +
                      "Valor original: R$" + String.format("%.2f", valor) + "\n" +
                      "Desconto: 10%\n" +
                      "Valor final: R$" + String.format("%.2f", calcularTotal());
        JOptionPane.showMessageDialog(null, recibo);
    }
}