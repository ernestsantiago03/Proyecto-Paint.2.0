/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import ingenieria1202610.PanelDeDibujo;

/**
 *
 * @author Argenis
 */
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Flecha extends Figura {

    private Point inicio;
    private Point fin;

    public Flecha(Point inicio) {
        this.inicio = inicio;
        this.fin = inicio;
    }

    @Override
    public void dibujar(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(color);
        g2.setStroke(new BasicStroke(2));

        g2.drawLine(inicio.x, inicio.y, fin.x, fin.y);

        int dx = fin.x - inicio.x;
        int dy = fin.y - inicio.y;

        double angulo = Math.atan2(dy, dx);
        int largo = 12;

        int x1 = (int) (fin.x - largo * Math.cos(angulo - Math.PI / 6));
        int y1 = (int) (fin.y - largo * Math.sin(angulo - Math.PI / 6));

        int x2 = (int) (fin.x - largo * Math.cos(angulo + Math.PI / 6));
        int y2 = (int) (fin.y - largo * Math.sin(angulo + Math.PI / 6));

        g2.drawLine(fin.x, fin.y, x1, y1);
        g2.drawLine(fin.x, fin.y, x2, y2);
    }

    @Override
    public void actualizar(Point p) {
        this.fin = p;
    }
}