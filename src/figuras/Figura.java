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
    protected Color colorBorde = Color.BLACK;
    protected Color colorRelleno = null;
    protected int grosor = 2;

    public void setColorBorde(Color colorBorde) {
        this.colorBorde = colorBorde;
    }

    public void setColorRelleno(Color colorRelleno) {
        this.colorRelleno = colorRelleno;
    }

    public void setGrosor(int grosor) {
        this.grosor = grosor;
    }

    public abstract void dibujar(Graphics g);
    
    public abstract void actualizar(Point puntoActual);
}
