/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
/**
 *
 * @author Osvaldo
 */

public class Linea extends Figura {

    private Point puntoInicial;
    private Point puntoFinal;

    public Linea(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoInicial;
    }

   
    public void dibujar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(grosor,
                     BasicStroke.CAP_ROUND,
                     BasicStroke.JOIN_ROUND));
        g2.drawLine(puntoInicial.x, puntoInicial.y,
                    puntoFinal.x, puntoFinal.y);
    }

    
    public void actualizar(Point puntoActual) {
        puntoFinal = puntoActual;
    }
}