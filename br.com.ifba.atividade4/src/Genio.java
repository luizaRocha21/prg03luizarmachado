/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author luiza
 */
/**
 * Classe que representa o gênio do jogo, responsável por sortear números e verificar palpites.
 */
public class Genio {
    private int numeroPensado;
    private int tentativas;

    public Genio() {
        this.tentarNovamente(); // Inicializa com um número aleatório
        this.tentativas = 0;
    }

    /**
     * Gera um novo número aleatório entre 1 e 5 usando Math.random()
     */
    public void tentarNovamente() {
        // Math.random() retorna um double entre 0.0 e 1.0
        // Multiplicamos por 5 para obter um número entre 0.0 e 5.0
        // Somamos 1 para ficar entre 1.0 e 6.0
        // Convertemos para int, que trunca para 1 a 5
        this.numeroPensado = (int)(Math.random() * 5) + 1;
    }

    /**
     * Verifica se o palpite do usuário está correto
     * @param palpite O número escolhido pelo usuário
     * @return Mensagem de acerto ou erro
     */
    public String verificarPalpite(int palpite) {
        this.tentativas++;
        
        if (palpite == this.numeroPensado) {
            return "Acertou!";
        } else {
            return "Errou! Pensei no valor " + this.numeroPensado + ".";
        }
    }

    /**
     * @return O número atual que o gênio está pensando
     */
    public int getNumeroPensado() {
        return this.numeroPensado;
    }

    /**
     * @return Quantidade de tentativas feitas
     */
    public int getTentativas() {
        return this.tentativas;
    }
}