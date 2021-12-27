/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistema.punto.de.ventas;

import ViewsForms.Sistema;
import java.awt.Frame;
import javax.swing.UIManager;


/**
 *
 * @author Stiven Arboleda
 */
public class SistemaPuntoDeVentas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLooAndFell");
        } catch (Exception ex) {
            
        } 
        Sistema sistema = new Sistema();
        sistema.setVisible(true);
        sistema.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
    
}
