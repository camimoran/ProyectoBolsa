/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.math.BigDecimal;
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
    
    public Accion traerAccion(String codClient,String company,BigDecimal price){
        String query = "SELECT * FROM Acciones WHERE codClient = ? AND company = ? AND price = ?";
        System.out.println("DAO"+codClient+company+price);
                    
        Accion a = new Accion();
        Conexion con = getConection();
        ResultSet result = null;
         try {
             PreparedStatement ps = con.conn.prepareStatement(query);
             ps.setString(1, codClient);
             ps.setString(2, company);
             ps.setBigDecimal(3, price);
             result = ps.executeQuery();
             while(result.next()){ 
                 a.setId(result.getInt("idAccion"));
                 a.setCompany(result.getString("company"));
                 a.setPrice(result.getBigDecimal("price"));
                 a.setQuantity(result.getInt("quantity"));
             }
             this.delteNoQuantityAccions();
         } catch (SQLException e) {    
             System.err.println(e.getMessage());
         }
         finally{
            con.close();
            return a; 
         }
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
             this.delteNoQuantityAccions();
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
            ps.setBigDecimal(3,c.getPrice()); 
            ps.setInt(4,c.getQuantity());
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally{
            con.close();
        }
    }

    public void modifyAction(Accion c) {
        String query = "UPDATE Acciones SET quantity = ? WHERE idAccion = ?";
        Conexion con = getConection();
        try{
            PreparedStatement ps = con.conn.prepareStatement(query);
            ps.setInt(1,c.getQuantity());
            ps.setInt(2,c.getId());
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally{
            con.close();
        }
        this.delteNoQuantityAccions();
    }
    
    private Accion crearConResult(ResultSet result) throws SQLException{
        Accion acc = new Accion(result.getInt("idAccion"), 
                result.getString("company"),
                result.getBigDecimal("price"),  // CONVERTIR A BIG DECIMAL.
                result.getInt("quantity"));
        return acc;
    }

    private void delteNoQuantityAccions(){
        String query = "DELETE FROM Acciones WHERE quantity <= ?";
        Conexion con = getConection();
        try {
            PreparedStatement ps = con.conn.prepareStatement(query);
            ps.setInt(1,0);
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally{
            con.close();
        }
    }

}
