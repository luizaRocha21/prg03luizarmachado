/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author luiza
 */

/**
 * Classe abstrata para formas tridimensionais.
 * Herda de Forma e adiciona métodos para área e volume.
 */

public abstract class Forma3D extends Forma {
    public abstract double obterArea();
    public abstract double obterVolume();
}