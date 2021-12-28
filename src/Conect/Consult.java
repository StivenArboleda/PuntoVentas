/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conect;

import Models.TClientes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

/**
 *
 * @author Stiven Arboleda
 */
public class Consult extends ConectBD{
    
    private QueryRunner QR = new QueryRunner();
    
    public List<TClientes> clientes(){
        
         List<TClientes> cliente = new ArrayList();
            
        try {
            
           cliente = (List<TClientes>) QR.query(getConn(), "SELECT * FROM tclientes", new BeanListHandler(TClientes.class));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex);
        }
        return cliente;
    }
    
}
