/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luiza
 */
import javax.swing.JOptionPane;
public class Cartao implements Pagamento {
    private double valor;
    
    public Cartao(double valor) {
        this.valor = valor;
    }
    
    @Override
    public double calcularTotal() {
        return valor * 1.05; // 5% de taxa
    }
    
    @Override
    public void imprimirRecibo() {
        String recibo = "RECIBO - Pagamento com Cartão\n" +
                       "Valor original: R$" + String.format("%.2f", valor) + "\n" +
                       "Taxa: 5%\n" +
                       "Valor final: R$" + String.format("%.2f", calcularTotal());
        JOptionPane.showMessageDialog(null, recibo);
    }
}
