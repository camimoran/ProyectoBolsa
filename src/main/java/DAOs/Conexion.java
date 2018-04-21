/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author 
 */
public class Conexion {
    public String url;
    Connection conn;
    
    public Conexion() throws ClassNotFoundException{
            // db parameters
            //url = "jdbc:sqlite:C:/Users/Yo/Downloads/Facundo/TRABAJO/DESARROLLO DE SOFTWARE/PROYECTOS/2018/ProyectoBolsa/ProyectoBolsaDB.db";
            url = "jdbc:sqlite:C:/Users/kamii/Desktop/ProyectoBolsa/ProyectoBolsaDB.db";
    }
    
    public void connect() {
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);            
            if (conn!=null){
                System.out.println("Connection to SQLite has been established.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  
    }
    
    public void close(){
        try{
            conn.close();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
     public void selectAll(){
        String sql = "SELECT Id, Nombre FROM Personas";
        
        try (
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("Nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     
     public List<String> selectPersonas(){
        String query = "SELECT codigo FROM Clientes";
        List<String> lista = new ArrayList<>();
        connect();
        ResultSet result = null;
         try {
             PreparedStatement ps = conn.prepareStatement(query);
             result = ps.executeQuery();
             while(result.next()){
                 lista.add(result.getString("codigo"));
             }
         } catch (Exception e) {    
             System.err.println(e.getMessage());
         }
         finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            return lista; 
         }
     }      
}
