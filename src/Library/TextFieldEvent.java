/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Stiven Arboleda
 */

/*
 * Solo puede escribir LETRAS en los campos de números
*/
public class TextFieldEvent {
    
    public void textKeyPress(KeyEvent event){
       char ca = event.getKeyChar();
       if((ca < 'a' || ca > 'z') && (ca < 'A' || ca > 'Z') && 
               (ca != (char) KeyEvent.VK_BACK_SPACE) && (ca != (char) KeyEvent.VK_SPACE)){
           
           event.consume();
           
       }
    }
    /*
 * Solo puede escribir NUMEROS en los campos de números
*/
    public void numberKeyPress(KeyEvent event){
        char ca = event.getKeyChar();
        if((ca < '0' || ca > '9') && (ca != (char) KeyEvent.VK_BACK_SPACE)){
            event.consume();
        }
    }
    
    /*
    VERIFICA QUE EL EMAIL SEA VALIDO
    */
    public boolean isEmail(String correo){
        Pattern pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        Matcher mat = pat.matcher(correo);
        return mat.find();
    }
}
