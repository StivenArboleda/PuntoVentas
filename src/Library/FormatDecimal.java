/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stiven Arboleda
 */
public class FormatDecimal {
    
    Number numero;
    DecimalFormat formateador = new DecimalFormat("###,###");
    
    public String decimal(double format){
        return formateador.format(format);
    }
    
    public double reconstruir(String format){
        try {
            numero = formateador.parse(format.replace(" ", ""));
        } catch (ParseException ex) {
            System.out.println("Error: " + ex);
        }
        return numero.doubleValue();
    }
    
}
