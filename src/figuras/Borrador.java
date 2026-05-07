/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package figuras;
/**
 *
 * @author Osvaldo
 */
import java.awt.BasicStroke; /* Para controlar el grosor del trazo del borrador*/
import java.awt.Color; /* Para usar Color.WHITE — el borrador siempre pinta blanco*/
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;/* */
import java.util.ArrayList;/*guarda los puntos por donde pasa el mouse*/

public class Borrador extends Figura {

    private ArrayList<Point> puntos = new ArrayList<>(); /*guarda los puntos por donde pasa el mouse*/

    
    public void dibujar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);  /*El borrador siempre pinta blanco*/
        g2.setStroke(new BasicStroke(20,  /*Grosor fijo de 20 — mucho más grande que el lápiz para que sea fácil borrar*/
                     BasicStroke.CAP_ROUND,
                     BasicStroke.JOIN_ROUND));

        for (int i = 1; i < puntos.size(); i++) { /*conecta los puntos consecutivos.La única diferencia es que el color es blanco*/
            Point p1 = puntos.get(i - 1);
            Point p2 = puntos.get(i);
            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    @Override
    public void actualizar(Point puntoActual) {
        puntos.add(puntoActual);
    }
}