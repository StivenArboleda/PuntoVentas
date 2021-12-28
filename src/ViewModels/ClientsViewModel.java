/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import Library.Objects;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Stiven Arboleda
 */
public class ClientsViewModel {
    
    private String accion = "insert";
    private final ArrayList<JLabel> _labels;
    private final ArrayList<JTextField> _textFields;

    public ClientsViewModel(Object[] objects, ArrayList<JLabel> labels, ArrayList<JTextField> textFiled) {
        _labels = labels;
        _textFields = textFiled;
        
    }
    /*
    * NO PUEDE ESTAR VACÍO EL CAMPO PARA EL 
    * ID, NOMBRE, APELLIDO, TELEFONO, EMAIL Y DIRECCION
    */
    public void registrarCliente(){
        if (_textFields.get(0).getText().equals("")) {
            _labels.get(0).setText("Ingresa el ID");
            _labels.get(0).setForeground(Color.RED);
            _textFields.get(0).requestFocus();
        }else{
            if (_textFields.get(1).getText().equals("")) {
            _labels.get(1).setText("Ingresa el NOMBRE");
            _labels.get(1).setForeground(Color.RED);
            _textFields.get(1).requestFocus();
            }else{
                if (_textFields.get(2).getText().equals("")) {
                _labels.get(2).setText("Ingresa el APELLIDO");
                _labels.get(2).setForeground(Color.RED);
                _textFields.get(2).requestFocus();
                }else
                    if (!Objects.events.isEmail(_textFields.get(3).getText())) {
                    _labels.get(3).setText("Ingresa un EMAIL valido");
                    _labels.get(3).setForeground(Color.RED);
                    _textFields.get(3).requestFocus();
                    }else{
                        if (_textFields.get(4).getText().equals("")) {
                        _labels.get(4).setText("Ingresa el TELEFONO");
                        _labels.get(4).setForeground(Color.RED);
                        _textFields.get(4).requestFocus();
                        }else{
                            if (_textFields.get(5).getText().equals("")) {
                            _labels.get(5).setText("Ingresa la DIRECCION");
                            _labels.get(5).setForeground(Color.RED);
                            _textFields.get(5).requestFocus();
                            }
                        }
                
                    }
                
                }
            
            }
        
        }

}
