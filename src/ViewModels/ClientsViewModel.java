/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import Conect.Consult;
import Library.Calendario;
import Library.Objects;
import Library.UploadImage;
import Models.TClientes;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

/**
 *
 * @author Stiven Arboleda
 */
public class ClientsViewModel extends Consult {
    
    private String _accion = "insert";
    private final ArrayList<JLabel> _labels;
    private final ArrayList<JTextField> _textFields;
    private final JCheckBox _checkBoxCredito;
    private final JTable _tableClient;
    private DefaultTableModel modelo1;
    private int idCliente = 0;
    private int _reg_por_pagina = 10, _num_pag = 1;
    
    public ClientsViewModel(Object[] objects, ArrayList<JLabel> labels, ArrayList<JTextField> textFiled) {
        _labels = labels;
        _textFields = textFiled;
        _checkBoxCredito = (JCheckBox) objects[0];
        _tableClient = (JTable) objects[1];
        
    }

    /*
    * NO PUEDE ESTAR VACÍO EL CAMPO PARA EL 
    * ID, NOMBRE, APELLIDO, TELEFONO, EMAIL Y DIRECCION
     */
    public void registrarCliente() throws SQLException {
        if (_textFields.get(0).getText().equals("")) {
            _labels.get(0).setText("Ingresa el ID");
            _labels.get(0).setForeground(Color.RED);
            _textFields.get(0).requestFocus();
        } else {
            if (_textFields.get(1).getText().equals("")) {
                _labels.get(1).setText("Ingresa el NOMBRE");
                _labels.get(1).setForeground(Color.RED);
                _textFields.get(1).requestFocus();
            } else {
                if (_textFields.get(2).getText().equals("")) {
                    _labels.get(2).setText("Ingresa el APELLIDO");
                    _labels.get(2).setForeground(Color.RED);
                    _textFields.get(2).requestFocus();
                } else if (!Objects.events.isEmail(_textFields.get(3).getText())) {
                    _labels.get(3).setText("Ingresa un EMAIL valido");
                    _labels.get(3).setForeground(Color.RED);
                    _textFields.get(3).requestFocus();
                } else {
                    if (_textFields.get(4).getText().equals("")) {
                        _labels.get(4).setText("Ingresa el TELEFONO");
                        _labels.get(4).setForeground(Color.RED);
                        _textFields.get(4).requestFocus();
                    } else {
                        if (_textFields.get(5).getText().equals("")) {
                            _labels.get(5).setText("Ingresa la DIRECCION");
                            _labels.get(5).setForeground(Color.RED);
                            _textFields.get(5).requestFocus();
                        } else {
                            int count;
                            List<TClientes> listEmail = clientes().stream().filter(u -> u.getEmail().equals(_textFields.get(3).getText())).
                                    collect(Collectors.toList());
                            count = listEmail.size();
                            
                            List<TClientes> listID = clientes().stream().filter(u -> u.getNumeroIdentidad().equals(_textFields.get(0).getText())).
                                    collect(Collectors.toList());
                            count += listID.size();
                            
                            switch (_accion) {
                                case "insert":
                                        try {
                                    if (count == 0) {
                                        Insert();
                                    } else {
                                        if (!listEmail.isEmpty()) {
                                            _labels.get(3).setText("Email ya registrado");
                                            _labels.get(3).setForeground(Color.red);
                                            _labels.get(3).requestFocus();
                                        }
                                        if (!listID.isEmpty()) {
                                            _labels.get(0).setText("ID ya registrado");
                                            _labels.get(0).setForeground(Color.red);
                                            _labels.get(0).requestFocus();
                                        }
                                    }
                                } catch (SQLServerException ex) {
                                    JOptionPane.showMessageDialog(null, ex);
                                }
                                break;
                            }
                        }
                    }
                    
                }
                
            }
            
        }
        
    }
    
    private void Insert() throws SQLException {
        
        try {
            
            final QueryRunner qr = new QueryRunner(true);
            getConn().setAutoCommit(false);
            byte[] image = UploadImage.getImageByte();
            if (image == null) {
                image = Objects.uploadImage.getTransFoto(_labels.get(6));
            }
            String sqlCliente = "INSERT INTO tclientes(NumeroIdentidad, Nombre, Apellido, Email, Telefono, Direccion, Credito, Fecha, Imagen) VALUES (?,?,?,?,?,?,?,?,?)";
            
            Object[] dataCliente = {
                _textFields.get(0).getText(),
                _textFields.get(1).getText(),
                _textFields.get(2).getText(),
                _textFields.get(3).getText(),
                _textFields.get(4).getText(),
                _textFields.get(5).getText(),
                _checkBoxCredito.isSelected(),
                new Calendario().getFecha(),
                image,};
            qr.insert(getConn(), sqlCliente, new ColumnListHandler(), dataCliente);
            
            String sqlReport = "INSERT INTO treportes_clientes (DeudaActual, FechaDeuda, UltimoPago, FechaPago, Ticket, FechaLimite, IdCliente) VALUES (?,?,?,?,?,?,?)";
            
            List<TClientes> cliente = clientes();
            Object[] dataReport = {
                0,
                "--/--/--",
                0,
                "--/--/--",
                0000000000,
                "--/--/--",
                cliente.get(cliente.size() - 1).getID(),};
            qr.insert(getConn(), sqlReport, new ColumnListHandler(), dataReport);
            getConn().commit();
            restart();
        } catch (SQLException e) {
            getConn().rollback();
            JOptionPane.showMessageDialog(null, e);
        }        
    }
    
    public void searchCLient(String campo){
        List<TClientes> clientesFilter;
        String[] titulos = {"ID", "Numero Identificación", "Nombre", "Apellido", "Email",
                            "Direccion", "Telefono", "Credito", "Image"};
        modelo1 = new DefaultTableModel(null, titulos);
        int inicio = (_num_pag -1) * _reg_por_pagina;
        
        if(campo.equals("")){
            
        }
    }
    
    public final void restart(){
        _accion = "insert";
        _textFields.get(0).setText("");
        _textFields.get(1).setText("");
        _textFields.get(2).setText("");
        _textFields.get(3).setText("");
        _textFields.get(4).setText("");
        _textFields.get(5).setText("");
        _checkBoxCredito.setSelected(false);
        _checkBoxCredito.setForeground(new Color(102, 102 , 102));
        
        //LABELS
        _labels.get(0).setText("Numero ID");
        _labels.get(0).setForeground(new Color(102, 102, 102));
        
        _labels.get(1).setText("Nombre");
        _labels.get(1).setForeground(new Color(102, 102, 102));
        
        _labels.get(2).setText("Apellido");
        _labels.get(2).setForeground(new Color(102, 102, 102));
        
        _labels.get(3).setText("Email");
        _labels.get(3).setForeground(new Color(102, 102, 102));
        
        _labels.get(4).setText("Teléfono");
        _labels.get(4).setForeground(new Color(102, 102, 102));
        
        _labels.get(5).setText("Dirección");
        _labels.get(5).setForeground(new Color(102, 102, 102));
        
        _labels.get(6).setIcon(new ImageIcon(getClass().getClassLoader().getResource("Reources/dog-png.png")));
    }
}
