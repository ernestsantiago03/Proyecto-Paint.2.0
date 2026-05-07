/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints; /* Para el suavizado del trazo.*/
/**
 *
 * @author Osvaldo
 */
   /* */
public class Linea extends Figura { /* Recibe color y grosor automáticamente*/

    private Point puntoInicial;  /*guarda el ponto  donde el usuario hizo clic */
    private Point puntoFinal;   /* guarda el ponto donde el usuario soltó el mouse*/
            /*El constructor */
    public Linea(Point puntoInicial) {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoInicial;
    }

              /*El método dibujar */
    public void dibujar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(grosor,
                     BasicStroke.CAP_ROUND,
                     BasicStroke.JOIN_ROUND));
         /*se dibuja una línea recta entre dos puntos*/
        g2.drawLine(puntoInicial.x, puntoInicial.y,
                    puntoFinal.x, puntoFinal.y);
    }

    
    public void actualizar(Point puntoActual) {
        puntoFinal = puntoActual;
    }
}