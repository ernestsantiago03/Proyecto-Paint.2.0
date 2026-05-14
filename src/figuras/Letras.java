/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Graphics;
import java.awt.Point;


/**
 *
 * @author Maria Del Carmen
 */
public class Letras extends Figura  {
    
    private String texto = "";
    private Point punto;

    public Letras(Point punto) {
        this.punto = punto;
    }

    public void agregarLetra(char c) {
        texto += c;
    }

    public void borrarUltima() {
        if (!texto.isEmpty()) {
            texto = texto.substring(0, texto.length() - 1);
        }
    }
    @Override
public void dibujar(Graphics g) {
    g.setColor(color);
    
     int x = punto.x;
    int y = punto.y;

    for (String linea : texto.split("\n")) {
        g.drawString(linea, x, y);
        y += g.getFontMetrics().getHeight(); //  Ahora el texto baja una línea con darle enter 
    }
}

@Override
public void actualizar(Point p) {
    // no hace nada
}
}
