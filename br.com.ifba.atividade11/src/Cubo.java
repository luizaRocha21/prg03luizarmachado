/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luiza
 */

/**
 * Representa um cubo com cálculos de área superficial e volume.
 * Implementa Forma3D.
 */

public class Cubo extends Forma3D {
    private double aresta;

    public Cubo(double aresta) {
        this.aresta = aresta;
    }

    @Override
    public double obterArea() {
        return 6 * aresta * aresta;
    }

    @Override
    public double obterVolume() {
        return aresta * aresta * aresta;
    }

    @Override
    public String toString() {
        return "Cubo com aresta de " + aresta + " unidades";
    }
}