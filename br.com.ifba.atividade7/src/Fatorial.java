/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author luiza
 */
public class Fatorial {
    private int valor;
    private int fatorial;
    private String formula;
    
    public void setValor(int n) {
        this.valor = n;
        calcularFatorial();
    }
    
    public int getFatorial() {
        return this.fatorial;
    }
    
    public String getFormula() {
        return this.formula;
    }
    
    private void calcularFatorial() {
        if (valor < 0) {
            fatorial = -1; // Indica erro
            formula = "Não existe fatorial de número negativo";
            return;
        }
        
        fatorial = 1;
        StringBuilder sb = new StringBuilder();
        
        for (int i = valor; i >= 1; i--) {
            fatorial *= i;
            sb.append(i);
            if (i > 1) {
                sb.append(" x ");
            }
        }
        
        sb.append(" = ").append(fatorial);
        formula = sb.toString();
    }
}
