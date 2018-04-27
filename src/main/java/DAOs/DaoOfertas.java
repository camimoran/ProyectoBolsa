/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Accion;

/**
 *
 * @author kamii
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
    
    public void addOffer(Accion accion,BigDecimal expectedBuyPrice,BigDecimal expectedSellPrice) {
        String query = "INSERT INTO Ofertas(idAccion,expectedBuyPrice,expectedSellPrice,company,price,quantity) VALUES (?,?,?,?,?,?)";
        Conexion con = getConection();
        try{
            PreparedStatement ps = con.conn.prepareStatement(query);
            ps.setInt(1, accion.getId());
            ps.setBigDecimal(2, expectedBuyPrice);
            ps.setBigDecimal(3,expectedSellPrice); 
            ps.setString(4, accion.getCompany());
            ps.setBigDecimal(5,accion.getPrice()); 
            ps.setInt(6,accion.getQuantity());
            ps.executeUpdate();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }finally{
            con.close();
        }
    }
    
    public void modifyOffer(Accion c) {
        String query = "UPDATE Ofertas SET quantity = ? WHERE idAccion = ?";
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
    }

}
