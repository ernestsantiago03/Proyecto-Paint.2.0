/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ingenieria1202610;
import figuras.DibujoLibre;
import figuras.Figura;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import figuras.Borrador;
import figuras.Linea;   
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
/**
 *
 * @author Santiago
 */
/* */
public class PanelDeDibujo extends JPanel {

    // lista de todas las figuras dibujadas
    private ArrayList<Figura> figuras = new ArrayList<>();
    // imagen en memoria donde se dibuja todo
    private BufferedImage imagen;
    //  campos para el lápiz
    private Figura figuraActual;
    private Color colorActual = Color.BLACK; /* color por defecto negro*/
    private String herramienta = "Ninguna";/* Controla qué herramienta está activa. Empieza en "Ninguna" para que no dibuje hasta que el usuario elija una herramienta.*/
    private int grosorActual = 2; /*El grosor del trazo. Por defecto 2 píxeles*/

           /* El constructor, Pone el fondo del panel en blanco — el color del canvas*/
    public PanelDeDibujo() {
        setBackground(Color.WHITE);

        //  Se ejecuta una sola vez cuando el usuario hace clic
       addMouseListener(new MouseAdapter() {
       //herramienta es lápiz — crea un DibujoLibre nuevo y lo agrega a la lista
    public void mousePressed(MouseEvent e) {
        switch (herramienta) {
            case "lapiz":
                figuraActual = new DibujoLibre();
                figuraActual.setColor(colorActual);
                figuraActual.setGrosor(grosorActual);
                figuras.add(figuraActual);
                break;
        // herramienta es línea — crea una Linea con el punto donde hizo clic como punto inicial.    
            case "linea":                              
                figuraActual = new Linea(e.getPoint());
                figuraActual.setColor(colorActual);
                figuraActual.setGrosor(grosorActual);
                figuras.add(figuraActual);
                break;
        //herramienta es borrador — crea un Borrador nuevo. No necesita color ni grosor porque siempre pinta blanco con grosor 20.
            case "borrador":
                figuraActual = new Borrador();
                figuras.add(figuraActual);
                break;

            default:
                // ninguna herramienta activa
                break;
        }
        repaint();
    }
});
        //  cuando el usuario arrastra el mouse
        addMouseMotionListener(new MouseAdapter() {
            
            public void mouseDragged(MouseEvent e) {
                if (figuraActual != null) {
                    figuraActual.actualizar(e.getPoint());
                    repaint();
                }
            }
        });
    }

    // métodos públicos para los botones
    public void setColorActual(Color color) {
        this.colorActual = color;
    }

    public void setHerramienta(String herramienta) {
        this.herramienta = herramienta;
    }

    public void setGrosor(int grosor) {
        this.grosorActual = grosor;
    }

    public Color getColorActual() {
        return colorActual;
    }

    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (imagen == null || imagen.getWidth() != getWidth()
                || imagen.getHeight() != getHeight()) {
            BufferedImage nueva = new BufferedImage(
                    Math.max(1, getWidth()),
                    Math.max(1, getHeight()),
                    BufferedImage.TYPE_INT_ARGB);
            
            Graphics2D ng = nueva.createGraphics();
            ng.setColor(Color.WHITE);
            ng.fillRect(0, 0, nueva.getWidth(), nueva.getHeight());
            if (imagen != null) ng.drawImage(imagen, 0, 0, null);
            ng.dispose();
            imagen = nueva;
        }
        Graphics2D g2 = imagen.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, imagen.getWidth(), imagen.getHeight());
        for (Figura figura : figuras) {
            figura.dibujar(g2);
        }
        g2.dispose();
        g.drawImage(imagen, 0, 0, null);
    }
}