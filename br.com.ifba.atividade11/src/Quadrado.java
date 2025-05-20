/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luiza
 */

/**
 * Representa um quadrado com cálculo de área baseado no lado.
 * Implementa Forma2D.
 */

public class Quadrado extends Forma2D {
    private double lado;

    public Quadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double obterArea() {
        return lado * lado;
    }

    @Override
    public String toString() {
        return "Quadrado com lado de " + lado + " unidades";
    }
}
