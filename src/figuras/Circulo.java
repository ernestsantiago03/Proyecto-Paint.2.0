/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Circulo extends Figura {

    private Point inicio;
    private Point fin;

    public Circulo(Point inicio) {
        this.inicio = inicio;
        this.fin = inicio;
    }

    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);

        int x = Math.min(inicio.x, fin.x);
        int y = Math.min(inicio.y, fin.y);
        int ancho = Math.abs(fin.x - inicio.x);
        int alto = Math.abs(fin.y - inicio.y);

        g2.drawOval(x, y, ancho, alto);
    }

    @Override
    public void actualizar(Point p) {
        this.fin = p;
    }
}