/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author luiza
 */
import javax.swing.JOptionPane;
public class Pix implements Pagamento {
    private double valor;
    
    public Pix(double valor) {
        this.valor = valor;
    }
    
    @Override
    public double calcularTotal() {
        return valor * 0.98; // 2% de cashback
    }
    
    @Override
    public void imprimirRecibo() {
        String recibo = "RECIBO - Pagamento via PIX\n" +
                      "Valor original: R$" + String.format("%.2f", valor) + "\n" +
                      "Cashback: 2%\n" +
                      "Valor final: R$" + String.format("%.2f", calcularTotal());
        JOptionPane.showMessageDialog(null, recibo);
    }
}
