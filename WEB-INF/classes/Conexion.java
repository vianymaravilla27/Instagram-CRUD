package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    Connection con;
    
    public Conexion()
        {
        String cadena="jdbc:mysql://localhost:1616/coinsproyect";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(cadena,"root","07630");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
            //Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    public Connection getConexion(){
        return con;
    }   
}