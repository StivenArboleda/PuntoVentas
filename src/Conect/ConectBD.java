/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Stiven Arboleda
 */
public class ConectBD {
    
    private String db = "Punto_de_ventas";
    private String user = "root";
    private String pass = "";
    private String urlMySQL = "jdbc:msql://localhost/" + db + "?SslMode=none";
    private String urlSQL = "jdbc:sqlserver://localhost:1433;databaseName=" + db + ";integratedSecurity=true;";
    private Connection conn = null;
    
    public ConectBD(){
        try {
             //obtenemos el driver de para mysql
            /* Class.forName("com.mysql.jdbc.Driver");
             conn = DriverManager.getConnection(this.urlMysql, this.user, this.password);*/
            
            //Conexion a SQL Server
            //obtenemos el driver de para SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn =DriverManager.getConnection(urlSQL);
             if (conn != null) {
                System.out.println("Conexión a la base de datos " + this.db + "  CONECTADO ");
            }
         } catch (ClassNotFoundException | SQLException ex) {
             System.out.println("Error? : " + ex);
         }
    }

    public Connection getConn() {
        return conn;
    }
    
}

