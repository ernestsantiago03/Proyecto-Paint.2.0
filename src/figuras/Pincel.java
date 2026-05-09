package figuras;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;

/**
 * Clase para la herramienta Pincel
 */
public class Pincel extends Figura {

    private ArrayList<Point> puntos = new ArrayList<>();

    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        // Suavizado para que el pincel se vea fluido
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorBorde);
        
        // Configuramos un trazo redondo. Multiplicamos el grosor para diferenciarlo del lápiz.
        g2.setStroke(new BasicStroke(grosor * 2, 
                     BasicStroke.CAP_ROUND, 
                     BasicStroke.JOIN_ROUND));

        // Dibujamos las líneas conectando todos los puntos registrados
        for (int i = 1; i < puntos.size(); i++) {
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