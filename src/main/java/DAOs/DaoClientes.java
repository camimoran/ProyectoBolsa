/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kamii
 */
public class DaoClientes {
    
    Conexion con;

    private void getConection(){
        try {
            con = new Conexion();
            con.connect();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
       //return con;
    }

    // CRUD CLIENTES
    public String traerUno(String codigo){
        String query = "SELECT * FROM Clientes WHERE codigo = ?";
        String a = "";
        getConection();
        ResultSet rs ;
        try {
            PreparedStatement ps = con.conn.prepareStatement(query);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while(rs.next()){
                a = rs.getString("codigo");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally{
            con.close();
            return a;
        }
    }

}
