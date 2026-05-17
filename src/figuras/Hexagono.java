package figuras;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Path2D;

public class Hexagono extends Figura {

    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public Hexagono(int x1, int y1, int x2, int y2, Color color, int grosor) {
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
        g2.draw(crearHexagono());
    }

    @Override
    public void actualizar(Point puntoActual) {
        this.x2 = puntoActual.x;
        this.y2 = puntoActual.y;
    }

    private Path2D crearHexagono() {
        double izquierda = Math.min(x1, x2);
        double derecha = Math.max(x1, x2);
        double arriba = Math.min(y1, y2);
        double abajo = Math.max(y1, y2);
        double ancho = Math.max(1.0, derecha - izquierda);
        double alto = Math.max(1.0, abajo - arriba);
        double centroX = (izquierda + derecha) / 2.0;
        double centroY = (arriba + abajo) / 2.0;
        double radioX = ancho / 2.0;
        double radioY = alto / 2.0;

        Path2D hexagono = new Path2D.Double();
        for (int i = 0; i < 6; i++) {
            double angulo = Math.toRadians(60 * i - 30);
            double vx = centroX + radioX * Math.cos(angulo);
            double vy = centroY + radioY * Math.sin(angulo);
            if (i == 0) {
                hexagono.moveTo(vx, vy);
            } else {
                hexagono.lineTo(vx, vy);
            }
        }

        hexagono.closePath();
        return hexagono;
    }
}
