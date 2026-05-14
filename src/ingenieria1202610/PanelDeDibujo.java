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
 * @author Osvaldo
 */
public class PanelDeDibujo extends JPanel {

    // Lista de todas las figuras dibujadas
    private ArrayList<Figura> figuras = new ArrayList<>();

    // Imagen en memoria donde se dibuja todo
    private BufferedImage imagen;

    // Campos para las herramientas
    private Figura figuraActual;
    private Color colorDePrimerPlano = Color.BLACK;
    private Color colorDeSegundoPlano = null;
    private String herramienta = "Ninguna";
    private int grosorActual = 3;

    // PAINT-0003: imagen de fondo para mostrar archivos abiertos
    private BufferedImage imagenFondo;

    public PanelDeDibujo() {
        setBackground(Color.WHITE);

        // Cuando el usuario hace clic
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
                        break;

                    case "linea":
                        figuraActual = new Linea(e.getPoint());
                        figuraActual.setColorBorde(colorDePrimerPlano);
                        figuraActual.setGrosor(grosorActual);
                        figuras.add(figuraActual);
                        break;

                    case "borrador":
                        figuraActual = new Borrador();
                        figuras.add(figuraActual);
                        break;

                    case "rectangulo":
                        figuraActual = new Rectangulo(e.getPoint());
                        figuraActual.setColorBorde(colorDePrimerPlano);
                        figuraActual.setColorRelleno(colorDeSegundoPlano);
                        figuras.add(figuraActual);
                        break;

                    case "balde":
                        // Verificar que la imagen ya existe
                        if (imagen != null) {
                            BoteDePintura balde = new BoteDePintura(puntoInicial, imagen);
                            balde.setColorBorde(colorDePrimerPlano);
                            balde.rellenar();
                            figuras.add(balde);
                        }

                    case "pincel":
                        figuraActual = new figuras.Pincel();
                        figuraActual.setColorBorde(colorDePrimerPlano);
                        figuraActual.setGrosor(grosorActual);
                        figuras.add(figuraActual);
                        break;

                    default:
                        break;
                }
                repaint();
            }
        });

        // Cuando el usuario arrastra el mouse
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (figuraActual != null) {
                    figuraActual.actualizar(e.getPoint());
                    repaint();
                }
            }
        });
    }

    // Métodos públicos para los botones
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

        // Fondo blanco
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, imagen.getWidth(), imagen.getHeight());

        // PAINT-0003: si hay imagen de fondo cargada, se dibuja primero
        if (imagenFondo != null) {
            g2.drawImage(imagenFondo, 0, 0, imagen.getWidth(), imagen.getHeight(), null);
        }

        // Dibuja las figuras encima de la imagen de fondo
        for (Figura figura : figuras) {
            figura.dibujar(g2);
        }

        g2.dispose();
        g.drawImage(imagen, 0, 0, null);
    }

    // PAINT-0003: carga una imagen externa en el lienzo (Abrir archivo)
    public void cargarImagen(BufferedImage img) {
        this.imagenFondo = img;
        this.imagen = null;
        this.figuras.clear();
        repaint();
    }

    // PAINT-0003: limpia el lienzo por completo (Nuevo lienzo)
    public void limpiar() {
        this.imagenFondo = null;
        this.figuras.clear();
        this.imagen = null;
        repaint();
    }

    // PAINT-0003: indica si el lienzo tiene contenido dibujado o imagen cargada
    public boolean estaModificado() {
        return !figuras.isEmpty() || imagenFondo != null;
    }

}