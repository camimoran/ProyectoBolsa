/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import DAOs.DaoClientes;

/**
 *
 * @author kamii
 */
public class ClientsController {
    DaoClientes dao = new DaoClientes();
    
   public String checkLogin(String cod){
       String cliente=dao.traerUno(cod);
       
        return cliente;
   }   
       
}
