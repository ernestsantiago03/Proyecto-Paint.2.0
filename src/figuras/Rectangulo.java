/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Santiago
 */
public class Rectangulo extends Figura{

    private Point inicio;
    private Point fin;

    public Rectangulo(Point inicio) {
        this.inicio = inicio;
        this.fin = inicio;
    }

    @Override
    public void actualizar(Point puntoActual) {
        this.fin = puntoActual;
    }

    @Override
    public void dibujar(Graphics g) {

        int x = Math.min(inicio.x, fin.x);
        int y = Math.min(inicio.y, fin.y);

        int ancho = Math.abs(fin.x - inicio.x);
        int alto = Math.abs(fin.y - inicio.y);

        // RELLENO
        if (colorRelleno != null) {
            g.setColor(colorRelleno);
            g.fillRect(x, y, ancho, alto);
        }

        // BORDE
        g.setColor(colorBorde);
        g.drawRect(x, y, ancho, alto);
    }
}
