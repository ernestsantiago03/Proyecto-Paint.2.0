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
import figuras.BoteDePintura;
import figuras.Linea;
import figuras.Rectangulo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Osvaldo
 */
public class PanelDeDibujo extends JPanel {
private ArrayList<Figura> figurasRehacer = new ArrayList<>();
    // lista de todas las figuras dibujadas
    private ArrayList<Figura> figuras = new ArrayList<>();
    // imagen en memoria donde se dibuja todo
    private BufferedImage imagen;
    // ← NUEVO: campos para el lápiz
    private Figura figuraActual;
    private Color colorDePrimerPlano = Color.BLACK;
    private Color colorDeSegundoPlano = null;
    private String herramienta = "Ninguna";
    private int grosorActual = 3;

    public PanelDeDibujo() {
        setBackground(Color.WHITE);

        //  cuando el usuario hace clic
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point puntoInicial = e.getPoint();
                switch (herramienta) {
                    case "lapiz":
                        figuraActual = new DibujoLibre();
                        figuraActual.setColorBorde(colorDePrimerPlano);
                        figuraActual.setGrosor(grosorActual);
                        figuras.add(figuraActual);
                        figurasRehacer.clear();
                        break;

                    case "linea":
                        figuraActual = new Linea(e.getPoint());
                        figuraActual.setColorBorde(colorDePrimerPlano);
                        figuraActual.setGrosor(grosorActual);
                        figuras.add(figuraActual);
                        figurasRehacer.clear();
                        break;

                    case "borrador":
                        figuraActual = new Borrador();
                        figuras.add(figuraActual);
                        figurasRehacer.clear();
                        break;

                    case "rectangulo":
                        figuraActual = new Rectangulo(e.getPoint());
                        figuraActual.setColorBorde(colorDePrimerPlano);
                        figuraActual.setColorRelleno(colorDeSegundoPlano);
                        figuras.add(figuraActual);
                        figurasRehacer.clear();
                        break;
                    case "balde":
                        // verificar que la imagen ya existe
                        if (imagen != null) {
                            BoteDePintura balde = new BoteDePintura(puntoInicial, imagen);
                            balde.setColorBorde(colorDePrimerPlano);
                            balde.rellenar();
                            // guardar el estado actual de la imagen en la lista
                            figuras.add(balde);
                               figurasRehacer.clear();
                        }
                        break; 
                    case "pincel": // <-- NUEVO CASO
                        figuraActual = new figuras.Pincel();
                        figuraActual.setColorBorde(colorDePrimerPlano);
                        figuraActual.setGrosor(grosorActual);
                        figuras.add(figuraActual);
                        figurasRehacer.clear();
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
    public void setColorDePrimerPlano(Color color) {
        this.colorDePrimerPlano = color;
    }

    public void setColorDeSegundoPlano(Color color) {
        this.colorDeSegundoPlano = color;
    }

    public void setHerramienta(String herramienta) {
        this.herramienta = herramienta;
    }

    public void setGrosor(int grosor) {
        this.grosorActual = grosor;
    }

    public Color getColorDePrimerPlano() {
        return colorDePrimerPlano;
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
            if (imagen != null) {
                ng.drawImage(imagen, 0, 0, null);
            }
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
    public void deshacer() {
    if (!figuras.isEmpty()) {
        Figura ultima = figuras.remove(figuras.size() - 1);
        figurasRehacer.add(ultima);
        repaint();
    }
}

public void rehacer() {
    if (!figurasRehacer.isEmpty()) {
        Figura figura = figurasRehacer.remove(figurasRehacer.size() - 1);
        figuras.add(figura);
        repaint();
    }
}
}
