/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author luiza
 */
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;
    import java.util.Random;

public class Vetor {
    private List<Integer> elementos;
    
    // Construtor padrão
    public Vetor() {
        this.elementos = new ArrayList<>();
    }
    
    // Método para inicializar com valores aleatórios
    public void inicializarComValoresAleatorios(int quantidade, int limiteSuperior) {
        Random rand = new Random();
        this.elementos.clear();
        for (int i = 0; i < quantidade; i++) {
            this.elementos.add(rand.nextInt(limiteSuperior) + 1);
        }
    }
    
    // Demais métodos necessários
    public void adicionar(int valor) {
        this.elementos.add(valor);
    }
    
    public int get(int indice) {
        return this.elementos.get(indice);
    }
    
    public int tamanho() {
        return this.elementos.size();
    }

    // Remove um elemento pelo índice
    public void remover(int indice) {
        if (indice >= 0 && indice < elementos.size()) {
            this.elementos.remove(indice);
        }
    }

    // Ordena o vetor em ordem crescente
    public void ordenar() {
        Collections.sort(this.elementos);
    }

    // Retorna uma cópia dos elementos como array
    public int[] getElementos() {
        return this.elementos.stream().mapToInt(i -> i).toArray();
    }

    // Retorna os elementos como lista
    public List<Integer> getElementosList() {
        return new ArrayList<>(this.elementos);
    }

    // Verifica se o vetor está vazio
    public boolean estaVazio() {
        return this.elementos.isEmpty();
    }

    // Limpa todos os elementos do vetor
    public void limpar() {
        this.elementos.clear();
    }

    // Retorna uma representação em string do vetor
    @Override
    public String toString() {
        return this.elementos.toString();
    }   
}
