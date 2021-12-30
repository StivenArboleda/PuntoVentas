/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.synth.SynthStyleFactory;

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
                
            try {
                BufferedImage bImage = ImageIO.read(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(bImage, "png", bos);
                imageByte = bos.toByteArray();
            } catch (IOException ex) {
                
            }
                        
        }
    }
    
    public byte [] getTransFoto(JLabel label){
        ByteArrayOutputStream baos = null;
        
        try {
            Icon ico = label.getIcon();
            BufferedImage bufferedImage = new BufferedImage(ico.getIconWidth(), ico.getIconHeight(),
                    BufferedImage.TYPE_INT_RGB);
            baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
        } catch (IOException e) {
            
        }
        return baos.toByteArray();
    }
    public void byteImage(JLabel label, byte[] imgFoto){
        try {
            Image foto;
            BufferedImage image;
            ByteArrayInputStream bis = new ByteArrayInputStream(imgFoto);
            image = ImageIO.read(bis);
            foto = new ImageIcon(image).getImage();
            foto = foto.getScaledInstance(140, 140, 1);
            label.setIcon(new ImageIcon(foto));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
