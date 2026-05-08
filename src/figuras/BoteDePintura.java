/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Santiago
 */
public class BoteDePintura extends Figura{
   
    private Point punto;
    private BufferedImage imagen;

    public BoteDePintura(Point punto, BufferedImage imagen) {
        this.punto = punto;
        this.imagen = imagen;
    }

    @Override
    public void dibujar(Graphics g) {
        // cada vez que se redibuja, vuelve a aplicar el relleno
        rellenar();
    }

    @Override
    public void actualizar(Point puntoActual) {
        // el balde no necesita actualizar
    }

    public void rellenar() {
        int x = punto.x;
        int y = punto.y;

        if (x < 0 || y < 0 || x >= imagen.getWidth() 
                || y >= imagen.getHeight()) return;

        int colorObjetivo = imagen.getRGB(x, y);
        int colorNuevo = colorBorde.getRGB();

        if (colorObjetivo == colorNuevo) return;

        Queue<Point> cola = new LinkedList<>();
        cola.add(new Point(x, y));

        while (!cola.isEmpty()) {
            Point p = cola.poll();
            int px = p.x;
            int py = p.y;

            if (px < 0 || py < 0 || px >= imagen.getWidth() 
                    || py >= imagen.getHeight()) continue;
            if (imagen.getRGB(px, py) != colorObjetivo) continue;

            imagen.setRGB(px, py, colorNuevo);

            cola.add(new Point(px + 1, py));
            cola.add(new Point(px - 1, py));
            cola.add(new Point(px, py + 1));
            cola.add(new Point(px, py - 1));
        }
    }
}
