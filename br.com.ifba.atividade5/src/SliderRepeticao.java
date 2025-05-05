/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author luiza
 */
/**
 *
 * @author luiza
 */
public class SliderRepeticao {
    
    // Método para gerar a sequência numérica baseada nos parâmetros
    public static String[] gerarSequencia(int inicio, int fim, int passo) {
        // Validação dos parâmetros
        if (passo <= 0) {
            return new String[]{"Erro: Passo deve ser maior que zero"};
        }
        
        if ((inicio < fim && passo < 0) || (inicio > fim && passo > 0)) {
            return new String[]{"Erro: Passo incompatível com a direção da contagem"};
        }
        
        // Calcula o tamanho do array necessário
        int tamanho;
        if (inicio <= fim) {
            tamanho = ((fim - inicio) / passo) + 1;
        } else {
            tamanho = ((inicio - fim) / passo) + 1;
        }
        
        // Cria e preenche o array com os números da sequência
        String[] sequencia = new String[tamanho];
        
        if (inicio <= fim) {
            for (int i = 0, num = inicio; num <= fim; i++, num += passo) {
                sequencia[i] = Integer.toString(num);
            }
        } else {
            for (int i = 0, num = inicio; num >= fim; i++, num -= passo) {
                sequencia[i] = Integer.toString(num);
            }
        }
        
        return sequencia;
    }
}