/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Vinicius
 */


public class DB {
    
    private static Connection conn = null;
    
    public static Connection conectar() {            
        String url = "jdbc:sqlite:db-hotel";
        // String url = "jdbc:sqlite:db-hotel";
        
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException ex) {    
                 Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
                
        }
        return conn;
    }
    
    public static void desconectar() {
        if (conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
                Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}


