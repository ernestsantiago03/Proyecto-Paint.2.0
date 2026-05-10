
package ingenieria1202610;

import java.awt.Color;

public class ControladorColores {
    private PanelDeDibujo panel;
    public static final Color ROSADO = Color.PINK;
    public static final Color VERDE = Color.GREEN;
    public static final Color ROJO = Color.RED;
    public static final Color AZUL = Color.BLUE;
    public static final Color NARANJA = Color.ORANGE;
    public static final Color AMARILLO = Color.YELLOW;
    public static final Color GRIS = Color.GRAY;
    public static final Color VIOLETA = new Color(102, 0, 204);

    public ControladorColores(PanelDeDibujo panel) {
        this.panel = panel;
    }

    public void aplicarColorSecundario(Color colorSeleccionado) {
        panel.setColorDePrimerPlano(colorSeleccionado);//Para mantener placa de colores fija y no afectar los principales. 
    }
}