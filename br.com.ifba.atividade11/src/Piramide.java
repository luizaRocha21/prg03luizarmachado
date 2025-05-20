/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luiza
 */

/**
 * Representa uma esfera com cálculos de área superficial e volume.
 * Implementa Forma3D.
 */

public class Piramide extends Forma3D {
    private double base;
    private double altura;
    private double alturaTriangulo;

    public Piramide(double base, double altura, double alturaTriangulo) {
        this.base = base;
        this.altura = altura;
        this.alturaTriangulo = alturaTriangulo;
    }

    @Override
    public double obterArea() {
        double areaBase = base * base;
        double areaLateral = 4 * (base * alturaTriangulo) / 2;
        return areaBase + areaLateral;
    }

    @Override
    public double obterVolume() {
        return (base * base * altura) / 3;
    }

    @Override
    public String toString() {
        return "Pirâmide de base quadrada com lado " + base + 
               ", altura " + altura + " e altura triangular lateral " + alturaTriangulo;
    }
}
