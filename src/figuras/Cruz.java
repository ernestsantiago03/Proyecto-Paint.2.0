package figuras;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Path2D;

public class Cruz extends Figura {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Cruz(int x1, int y1, int x2, int y2, Color color, int grosor) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.grosor = grosor;
    }

    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(grosor));
        g2.draw(crearCruz());
    }

    @Override
    public void actualizar(Point puntoActual) {
        this.x2 = puntoActual.x;
        this.y2 = puntoActual.y;
    }

    private Path2D crearCruz() {
        double izquierda = Math.min(x1, x2);
        double derecha = Math.max(x1, x2);
        double arriba = Math.min(y1, y2);
        double abajo = Math.max(y1, y2);
        double ancho = Math.max(1.0, derecha - izquierda);
        double alto = Math.max(1.0, abajo - arriba);
        double grosorBrazo = Math.max(1.0, Math.min(ancho, alto) / 3.0);
        double centroX = (izquierda + derecha) / 2.0;
        double centroY = (arriba + abajo) / 2.0;
        double mitadBrazo = grosorBrazo / 2.0;

        Path2D cruz = new Path2D.Double();
        cruz.moveTo(centroX - mitadBrazo, arriba);
        cruz.lineTo(centroX + mitadBrazo, arriba);
        cruz.lineTo(centroX + mitadBrazo, centroY - mitadBrazo);
        cruz.lineTo(derecha, centroY - mitadBrazo);
        cruz.lineTo(derecha, centroY + mitadBrazo);
        cruz.lineTo(centroX + mitadBrazo, centroY + mitadBrazo);
        cruz.lineTo(centroX + mitadBrazo, abajo);
        cruz.lineTo(centroX - mitadBrazo, abajo);
        cruz.lineTo(centroX - mitadBrazo, centroY + mitadBrazo);
        cruz.lineTo(izquierda, centroY + mitadBrazo);
        cruz.lineTo(izquierda, centroY - mitadBrazo);
        cruz.lineTo(centroX - mitadBrazo, centroY - mitadBrazo);
        cruz.closePath();
        return cruz;
    }
}
