/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conect;

import Models.TClientes;
import Models.TConfiguration;
import Models.TReportes_clientes;
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
    public List<TReportes_clientes> reportesClientes(){
        String where = "";
        
        List<TReportes_clientes> reportes = new ArrayList();
        String condicion = " tclientes.ID = treportes_clientes.IdCliente";
        String campos = " tclientes.ID,tclientes.NumeroIdentidad,tclientes.Nombre,tclientes.Apellido,"
                + "treportes_clientes.IdRegistro, treportes_clientes.DeudaActual,"
                + "treportes_clientes.FechaDeuda, treportes_clientes.UltimoPago,"
                + "treportes_clientes.FechaPago, treportes_clientes.Ticket,"
                + "treportes_clientes.FechaLimite";
        try {
            reportes = (List<TReportes_clientes>) QR.query(getConn(), 
                    "SELECT" + campos + " FROM treportes_clientes Inner Join tclientes ON"
            +condicion + where, new BeanListHandler(TReportes_clientes.class));
        } catch (SQLException e) {
            System.out.println("Error :" + e);
        }
        return reportes;
    }
    
    public List<TConfiguration> config(){
        List<TConfiguration> config = new ArrayList();
        try {
            config = (List<TConfiguration>) QR.query(getConn(), "SELECT * FROM tconfiguration", 
                                            new BeanListHandler(TConfiguration.class));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return config;
    }
}
