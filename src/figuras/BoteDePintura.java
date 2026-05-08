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
public class BoteDePintura extends Figura{
    protected Point inicio;
    protected Point fin;

    public BoteDePintura (Point inicio) {
        this.inicio = inicio;
        this.fin = inicio;
    }

    @Override
    public void actualizar(Point puntoActual) {
        this.fin = puntoActual;
    }

    // Método para pintar relleno
    protected void pintarRelleno(Graphics g, int x, int y, int ancho, int alto) {

        if (colorRelleno != null) {
            g.setColor(colorRelleno);
            g.fillRect(x, y, ancho, alto);
        }
    }

    // Método para pintar borde
    protected void pintarBorde(Graphics g, int x, int y, int ancho, int alto) {

        g.setColor(colorBorde);

        for (int i = 0; i < grosor; i++) {
            g.drawRect(
                    x - i,
                    y - i,
                    ancho + (i * 2),
                    alto + (i * 2)
            );
        }
    }

    @Override
    public void dibujar(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
