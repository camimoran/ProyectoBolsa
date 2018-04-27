/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import DAOs.DaoAcciones;
import DAOs.DaoOfertas;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import models.Accion;


/**
 *
 * @author kamii
 */
public class AccionsController {
    DaoAcciones dao = new DaoAcciones();
    DaoOfertas daoOf = new DaoOfertas();
       
    public Accion traerAccion (String codClient,String company,BigDecimal price){
        return dao.traerAccion(codClient, company, price);
    }
            
    public List<Accion> traerAccionesCliente(String codCient){
        List<Accion> lista = new ArrayList();
        try {
            lista = dao.traerAccionesCliente(codCient);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally{
            return lista;
        }
    }

    // AGREGAR ACCION.
    public void comprarAccion(String codClient, String company, BigDecimal price, int quantity,BigDecimal stopLoss){
        // busca las acciones del cliente
        List<Accion> list = this.traerAccionesCliente(codClient);
        Accion a = new Accion(company,price,quantity);
        
        // accion obtendra null si no existe una accion con un precio y compania igual en la cartera del cliente
        // accion obtendra una cantidad actualizada si existe una accion identica en la cartera del cliente.
        Accion accion = searchIfActionExists(list,a);
        if(accion == null){
            dao.addAction(a,codClient);            
            if(stopLoss != null){
                daoOf.addOffer(traerAccion(codClient, company, price), null, stopLoss);
            }
        }else{
            //SE TIENE QUE AGREGAR A OFERTA CON EL ID DE ACCION,y CANTIDADES, 
            //Y SI ES VENTA QUANT ES NEGATIVO, PERO QUE PASA CON LA TABLA OFERTA ESTA RELACIONADA A LA
            // ACCIONES, QUE YA NO VAN A TENER LAS CANTIDAD QUE SE PONE EN EL TEXT,Y EN LA VENTA NO VA A 
            // ENCONTRAR L ACCION SI SE VENDIO , CREO QUE HAY QUE PONER LOS DATOS EN LA TABLA OFERTA       
            //-----------
            //CUANDO SE COMPRA UNA ACCION QUE YA TENGO CON STOPLOSS TIENE QUE SUMARSE 
            //A LA TABLA DE STOPLOSS (SI TIENE EL MISMO PRECIO, STOPLOSS,COMPANIA)?    
            //Y CUANDO SE VENDE UNA ACCION QUE YA TENGO CON STOPLOSS TIENE QUE SUMARSE 
            //A LA TABLA DE STOPLOSS (SI TIENE EL MISMO PRECIO, STOPLOSS,COMPANIA)?
            dao.modifyAction(accion);    
            if(stopLoss != null){
                if(quantity<0){
                    accion.setQuantity(quantity*-1);
                    daoOf.addOffer(accion, stopLoss, null);
                }else{
                    accion.setQuantity(quantity);
                    daoOf.addOffer(accion, null, stopLoss);                    
                }
            }
        }  
    }

    // compara la nueva accion para ver si ya existe una de la misma compania y precio en la cartera del cliente.
    //  si existe retorna la accion con la cantidad actualizada , sino devuelve null
    private Accion searchIfActionExists(List<Accion> clientActions , Accion action){
        for (Accion a: clientActions) {
            if ((a.getCompany().equals(action.getCompany())) && (a.getPrice().equals(action.getPrice())) ){
                int newQuantity = action.getQuantity() + a.getQuantity();
                action.setQuantity(newQuantity);
                action.setId(a.getId());
                return action;
            }
        }
        return null;
    }

}
