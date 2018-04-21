/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import DAOs.DaoAcciones;
import java.util.ArrayList;
import java.util.List;
import models.Accion;


/**
 *
 * @author kamii
 */
public class AccionsController {
    DaoAcciones dao = new DaoAcciones();
       
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
    public void comprarAccion(String codClient, String company, int price, int quantity){

        // busca las acciones del cliente
        List<Accion> list = this.traerAccionesCliente(codClient);
        Accion a = new Accion(company,price,quantity);

        // accion obtendra null si no existe una accion con un precio y compania igual en la cartera del cliente
        // accion obtendra una cantidad actualizada si existe una accion identica en la cartera del cliente.
        Accion accion = searchIfActionExists(list,a);
        if(accion == null){
            dao.modifyAction(accion);
        }else{
            dao.addAction(accion,codClient);
        }
    }

    // compara la nueva accion para ver si ya existe una de la misma compania y precio en la cartera del cliente.
    //  si existe retorna la accion con la cantidad actualizada , sino devuelve null
    private Accion searchIfActionExists(List<Accion> clientActions , Accion action){
        for (Accion a: clientActions) {
            if ((a.getCompany() == action.getCompany())&&(a.getPrice() == action.getPrice())){
                int newQuantity = action.getQuantity() + a.getQuantity();
                action.setQuantity(newQuantity);
                return action;
            }
        }
        return null;
    }

    public void venderAccion(String codClient, String company, int price, int quantity, int selledActionsQuantity){
        quantity = quantity - selledActionsQuantity;
        dao.modifyAction(new Accion(company,price,quantity));
    }

}
