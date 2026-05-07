/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;         /* Representa una coordenada (x, y) en la pantalla */
import java.awt.RenderingHints;
import java.util.ArrayList;
/**
 *
 * @author Osvaldo
 */
public class DibujoLibre extends Figura {          /* */

    private ArrayList<Point> puntos = new ArrayList<>();  /* Guarda todos los puntos por donde pasó el mouse*/

    
    public void dibujar(Graphics g) {    /*Lo llama PanelDeDibujo en el paintComponent cada vez que se redibuja la pantalla */
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);  /* suaviza los bordes del trazo para que se vea más limpio y profesional*/
        g2.setColor(color);  /* Usa el campo color heredado de Figura*/
        g2.setStroke(new BasicStroke(grosor, /* heredado de Figura, controla el ancho del trazo*/
                     BasicStroke.CAP_ROUND,  /*las puntas del trazo son redondeadas, no cuadradas */
                     BasicStroke.JOIN_ROUND)); /*las puntas del trazo son redondeadas, no cuadradas */

        for (int i = 1; i < puntos.size(); i++) {  /*Recorre la lista de puntos desde el segundo (i = 1) */
            Point p1 = puntos.get(i - 1);         /*punto anterior */
            Point p2 = puntos.get(i);             /*punto actual */
            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
 
    public void actualizar(Point puntoActual) { /*Lo llama PanelDeDibujo en el mouseDragged() cada vez que el mouse se arrastra*/
        puntos.add(puntoActual);
    }
}
