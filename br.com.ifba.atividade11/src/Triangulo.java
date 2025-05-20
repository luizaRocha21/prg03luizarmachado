/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luiza
 */

/**
 * Representa um triângulo com cálculo de área baseado na base e altura.
 * Implementa Forma2D.
 */

public class Triangulo extends Forma2D {
    private double base;
    private double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double obterArea() {
        return (base * altura) / 2;
    }

    @Override
    public String toString() {
        return "Triângulo com base " + base + " e altura " + altura;
    }
}
