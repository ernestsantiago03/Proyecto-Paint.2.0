/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package figuras;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago
 */
public class ControladorCierre { 

    public static void confirmarCierre(JFrame frame) {
        int opcion = JOptionPane.showConfirmDialog(
    frame,
    "¿Seguro que quieres salir?",
    "Confirmar salida",
    JOptionPane.YES_NO_CANCEL_OPTION,
    JOptionPane.QUESTION_MESSAGE
);
        if (opcion == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
}
