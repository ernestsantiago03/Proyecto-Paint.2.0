package ingenieria1202610;

import figuras.Cruz;
import figuras.Figura;
import figuras.Hexagono;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class PanelDeDibujo extends JPanel {

    private final ArrayList<Figura> figuras = new ArrayList<>();

    private Color colorActual = Color.BLACK;
    private String herramienta = "NINGUNA";
    private int grosorActual = 2;
    private Figura figuraActual;

    private BufferedImage imagen;

    public PanelDeDibujo() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                figuraActual = null;

                if (herramienta.equals("CRUZ")) {
                    figuraActual = new Cruz(
                            e.getX(),
                            e.getY(),
                            e.getX(),
                            e.getY(),
                            colorActual,
                            grosorActual
                    );
                }

                if (herramienta.equals("HEXAGONO")) {
                    figuraActual = new Hexagono(
                            e.getX(),
                            e.getY(),
                            e.getX(),
                            e.getY(),
                            colorActual,
                            grosorActual
                    );
                }

                if (figuraActual != null) {
                    figuras.add(figuraActual);
                    repaint();
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (figuraActual != null) {
                    figuraActual.actualizar(e.getPoint());
                    figuraActual = null;
                    repaint();
                }
            }
        });

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent e) {
                if (figuraActual != null) {
                    figuraActual.actualizar(e.getPoint());
                    repaint();
                }
            }
        });
    }

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

    @Override
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
}

