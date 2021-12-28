/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Stiven Arboleda
 */
public class UploadImage extends JFrame{
    
    private File file;
    private JFileChooser openFile;
    private static String urlOrigen = null;
    private static byte[] imageByte = null;

    public static byte[] getImageByte() {
        return imageByte;
    }
         
    public void UploadImageMethod(JLabel label){
        openFile = new JFileChooser();
        openFile.setFileFilter(new FileNameExtensionFilter("Archivos de imagen", "jpg","png", "gif"));
        
        int answer = openFile.showOpenDialog(this);
        if(answer == JFileChooser.APPROVE_OPTION){
            file = openFile.getSelectedFile();
            urlOrigen = file.getAbsolutePath();
            Image photo = getToolkit().getImage(urlOrigen);
            photo = photo.getScaledInstance(140, 140, 1);
            label.setIcon(new ImageIcon(photo));
            imageByte = new byte[(int) file.length()];
            
            
        }
    }
    
}
