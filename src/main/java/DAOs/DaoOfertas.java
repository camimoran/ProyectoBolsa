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
 * @author 
 */
public class DaoOfertas {
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
    
    public void addOffer(Accion accion,String codClient,BigDecimal expectedBuyPrice,BigDecimal expectedSellPrice) {
        String query = "INSERT INTO Ofertas(idAccion,expectedBuyPrice,expectedSellPrice,codClient,company,price,quantity) VALUES (?,?,?,?,?,?,?)";
        Conexion con = getConection();
        try{
            PreparedStatement ps = con.conn.prepareStatement(query);
            ps.setInt(1, accion.getId());
            ps.setBigDecimal(2, expectedBuyPrice);
            ps.setBigDecimal(3,expectedSellPrice); 
            ps.setString(4, codClient) ;
            ps.setString(5, accion.getCompany());
            ps.setBigDecimal(6,accion.getPrice()); 
            ps.setInt(7,accion.getQuantity());
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally{
            con.close();
        }
    }
    
    public Accion traerOff (String codClient,String company,BigDecimal price){
        String query = "SELECT * FROM Ofertas WHERE codClient = ? AND company = ? AND price = ? AND expectedSellPrice not null";
        Accion acc = null;
        Conexion con = getConection();
        ResultSet result = null;
         try {
             PreparedStatement ps = con.conn.prepareStatement(query);
             ps.setString(1, codClient);
             ps.setString(2, company);
             ps.setBigDecimal(3, price);
             result = ps.executeQuery();
             while(result.next()){
                 acc= new Accion();
                 acc.setId(result.getInt("idAccion")); 
                 acc.setCompany(result.getString("company")); 
                 acc.setPrice(result.getBigDecimal("expectedBuyPrice")); 
                 acc.setQuantity(result.getInt("quantity")); 
                 acc.setIdOffer(result.getInt("idOffer")); 
                 acc.setCodCliente(result.getString("codClient")); 
             }
         } catch (SQLException e) {    
             System.err.println(e.getMessage());
         }
         finally{
            con.close();
            return acc; 
         }
    }
            
    public List<Accion> traerOfertasSell(){
        String query = "SELECT * FROM Ofertas WHERE expectedSellPrice not null";
        List<Accion> lista = new ArrayList<Accion>();
        Conexion con = getConection();
        ResultSet result = null;
         try {
             PreparedStatement ps = con.conn.prepareStatement(query);
             result = ps.executeQuery();
             while(result.next()){
                 Accion acc= new Accion();
                 acc.setId(result.getInt("idAccion")); 
                 acc.setCompany(result.getString("company")); 
                 acc.setPrice(result.getBigDecimal("expectedSellPrice")); 
                 acc.setQuantity(result.getInt("quantity")); 
                 acc.setIdOffer(result.getInt("idOffer")); 
                 acc.setCodCliente(result.getString("codClient")); 
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
     
    public List<Accion> traerOfertasBuy(){
        String query = "SELECT * FROM Ofertas WHERE expectedBuyPrice not null";
        List<Accion> lista = new ArrayList<Accion>();
        Conexion con = getConection();
        ResultSet result = null;
         try {
             PreparedStatement ps = con.conn.prepareStatement(query);
             result = ps.executeQuery();
             while(result.next()){
                 Accion acc= new Accion();
                 acc.setId(result.getInt("idAccion")); 
                 acc.setCompany(result.getString("company")); 
                 acc.setPrice(result.getBigDecimal("expectedBuyPrice")); 
                 acc.setQuantity(result.getInt("quantity")); 
                 acc.setIdOffer(result.getInt("idOffer")); 
                 acc.setCodCliente(result.getString("codClient")); 
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
     
    public void deleteOffer(int idOffer){
        String query = "DELETE FROM Ofertas WHERE idOffer = ?";
        Conexion con = getConection();
        try {
            PreparedStatement ps = con.conn.prepareStatement(query);
            ps.setInt(1,idOffer);
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally{
            con.close();
        }
    }

    public void modifyOffer(Accion c) {
        String query = "UPDATE Ofertas SET quantity = ? WHERE idOffer = ?";
        Conexion con = getConection();
        try{
            PreparedStatement ps = con.conn.prepareStatement(query);
            ps.setInt(1,c.getQuantity());
            ps.setInt(2,c.getIdOffer());
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally{
            con.close();
            this.deleteNoQuantityOffers();
        }
    }

    private void deleteNoQuantityOffers(){
        String query = "DELETE FROM Ofertas WHERE quantity <= ?";
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
