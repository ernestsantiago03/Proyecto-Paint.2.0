package figuras;

import ingenieria1202610.PanelDeDibujo;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * PAINT-0003 - Ronald
 * Clase que maneja las funciones de Abrir archivo y Nuevo lienzo
 * del menú Archivo de la aplicación.
 */
public class AbrirArchivo {

    /**
     * PAINT-0003: Abre una ventana para que el usuario busque y seleccione
     * una imagen PNG o JPG desde su computadora, y la carga en el lienzo.
     */
    public static void abrir(PanelDeDibujo panel, JFrame ventana) {

        // Crea el selector de archivos con título personalizado
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Abrir imagen");

        // Limita el selector para mostrar solo imágenes PNG y JPG
        selector.setFileFilter(
            new FileNameExtensionFilter("Imágenes (PNG, JPG)", "png", "jpg", "jpeg")
        );

        // Muestra la ventana de selección y espera la respuesta del usuario
        int resultado = selector.showOpenDialog(ventana);

        // Solo continúa si el usuario seleccionó un archivo y dio clic en Abrir
        if (resultado == JFileChooser.APPROVE_OPTION) {
            try {
                // Obtiene el archivo seleccionado por el usuario
                File archivo = selector.getSelectedFile();

                // Lee el archivo y lo convierte en una imagen
                BufferedImage imagen = ImageIO.read(archivo);

                if (imagen != null) {
                    // Envía la imagen al lienzo para que se muestre
                    panel.cargarImagen(imagen);
                } else {
                    // Si el archivo no es una imagen válida, avisa al usuario
                    JOptionPane.showMessageDialog(ventana,
                        "El archivo seleccionado no es una imagen válida.",
                        "Error al abrir",
                        JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                // Si ocurre cualquier otro error, muestra el mensaje correspondiente
                JOptionPane.showMessageDialog(ventana,
                    "Error al abrir el archivo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * PAINT-0003: Limpia el lienzo para empezar desde cero.
     * Si hay contenido en el lienzo, pide confirmación al usuario
     * para evitar perder trabajo por accidente.
     * Si el lienzo está vacío, limpia directamente sin preguntar.
     */
    public static void nuevo(PanelDeDibujo panel, JFrame ventana) {

        // Solo pregunta si hay algo dibujado o cargado en el lienzo
        if (panel.estaModificado()) {

            // Muestra un diálogo preguntando si el usuario está seguro
            int respuesta = JOptionPane.showConfirmDialog(ventana,
                "¿Deseas crear un nuevo lienzo?\nSe perderán los cambios actuales.",
                "Nuevo lienzo",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

            // Solo limpia el lienzo si el usuario confirmó con "Sí"
            if (respuesta == JOptionPane.YES_OPTION) {
                panel.limpiar();
            }

        } else {
            // El lienzo está vacío, no tiene sentido preguntar
            panel.limpiar();
        }
    }

}