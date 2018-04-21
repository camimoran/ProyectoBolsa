/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Accion;

/**
 *
 * @author kamii
 */
public class DaoAcciones {
    
    Conexion con;

    private Conexion getConection(){
        try {
            con = new Conexion();
            con.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
        
    public List<Accion> traerAccionesCliente(String codClient){
        String query = "SELECT * FROM Acciones WHERE codClient = ?";
        List<Accion> lista = new ArrayList<Accion>();
        Conexion con = getConection();
        ResultSet result = null;
         try {
             PreparedStatement ps = con.conn.prepareStatement(query);
             ps.setString(1, codClient);
             result = ps.executeQuery();
             while(result.next()){
                 Accion acc = crearConResult(result);
                 lista.add(acc);
             }
         } catch (SQLException e) {    
             System.err.println(e.getMessage());
         }
         finally{
            con.close();
            return lista; 
         }
    }
    
    public void addAction(Accion c,String codClient) {
        String query = "INSERT INTO Acciones(codClient,company,price,quantity) VALUES (?,?,?,?)";
        Conexion con = getConection();
        try{
            PreparedStatement ps = con.conn.prepareStatement(query);
            ps.setString(1, codClient);
            ps.setString(2, c.getCompany());
            ps.setFloat(3,c.getPrice());
            ps.setFloat(4,c.getQuantity());
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally{
            con.close();
        }
    }

    // El id de Acciones tiene que traer todos los datos. (en este caso falta Id de la bdd para indicar donde va el update).
    public void modifyAction(Accion c) {
        String query = "UPDATE Acciones SET quantity = ? WHERE idAccion = ?";
        Conexion con = getConection();
        try{
            PreparedStatement ps = con.conn.prepareStatement(query);
            ps.setInt(1,c.getQuantity());
            ps.setInt(2,c.getId());
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally{
            con.close();
        }

    }
    
    private Accion crearConResult(ResultSet result) throws SQLException{
        Accion acc = new Accion(result.getInt("idAccion"), 
                result.getString("company"), 
                result.getFloat("price"), 
                result.getInt("quantity"));
               
        return acc;
    }

}
