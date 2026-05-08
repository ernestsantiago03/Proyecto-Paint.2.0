/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Osvaldo
 */
public abstract class Figura {
    protected Color color = Color.BLACK;      // color genérico
    protected Color colorBorde = Color.BLACK; // 
    protected Color colorRelleno = null;      // (null = sin relleno)
    protected int grosor = 2;

    public void setColor(Color color) {
        this.color = color;
        this.colorBorde = color; // sincroniza con colorBorde
    }

    public void setColorBorde(Color color) {  
        this.colorBorde = color;
    }

    public void setColorRelleno(Color color) { 
        this.colorRelleno = color;
    }

    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }

    public abstract void dibujar(Graphics g);
    public abstract void actualizar(Point puntoActual);
}