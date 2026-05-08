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
public class Rectangulo extends BoteDePintura{

    public Rectangulo(Point inicio) {
        super(inicio);
    }

    @Override
    public void dibujar(Graphics g) {

        int x = Math.min(inicio.x, fin.x);
        int y = Math.min(inicio.y, fin.y);

        int ancho = Math.abs(fin.x - inicio.x);
        int alto = Math.abs(fin.y - inicio.y);

        // RELLENO
        pintarRelleno(g, x, y, ancho, alto);

        // BORDE
        pintarBorde(g, x, y, ancho, alto);
    } 
}
