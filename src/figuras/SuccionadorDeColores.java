/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Yarlenis
 */
public class SuccionadorDeColores {
    
    public static Color capturarColor(BufferedImage imagen, int x, int y) {
        // Validación de seguridadnpara evitar que el programa explote si el clic cae fuera
        if (imagen != null && x >= 0 && x < imagen.getWidth() && y >= 0 && y < imagen.getHeight()) {
            // Para obtener el color exacto del píxel en la imagen del lienzo
            return new Color(imagen.getRGB(x, y), true);
        }
        return Color.BLACK; // Color por defecto para evitar que el programa falle
    }
    public static Cursor obtenerCursorCuentagotas() {
        try {
            // Cargamos el icono desde tu carpeta de recursos
            URL url = (SuccionadorDeColores.class.getResource("/iconos/icons8-cuentagotas-de-color-24.png"));

            if (url != null) {
                ImageIcon iconoOriginal = new ImageIcon(url);
                // Para que el cursor tenga un tamaño adecuado y se vea bien
                Image imgEscalada = iconoOriginal.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);

                Toolkit tk = Toolkit.getDefaultToolkit();
                // El Point(0, 31) pone la "punta" de acción abajo a la izquierda del icono
                return tk.createCustomCursor(imgEscalada, new Point(0, 31), "Cuentagotas");
            }
            // Definimos el HotSpot (la punta del cuentagotas)

        } catch (Exception e) {

        }
        // Si falla la carga, usamos la cruz estándar de dibujo
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }
    
}
