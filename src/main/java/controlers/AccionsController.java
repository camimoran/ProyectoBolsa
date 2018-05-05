/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import API.ApiTrader;
import DAOs.DaoAcciones;
import DAOs.DaoOfertas;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import models.Accion;


/**
 *
 * @author 
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
                daoOf.addOffer(traerAccion(codClient, company, price),codClient, null, stopLoss);
            }
        }else{
            dao.modifyAction(accion);
            //SI ES UNA VENTA SE TIENE QUE VERIFICAR SI TENIA OFERTA Y MODIFICAR 
            if(quantity<0){
                Accion oferta = daoOf.traerOff(codClient, company,price);
                if(oferta != null){
                    oferta.setQuantity(oferta.getQuantity() +quantity);
                    daoOf.modifyOffer(oferta);
                }
            }
            
            if(stopLoss != null){
                //Se setea la cantidad porque al modificar se le pasa la cantidad actual de la accion
                //Si quantity es negativo es una venta
                if(quantity<0){
                    //se pone quantity en positivo para agregarlo a la table oferta como positivo
                    accion.setQuantity(quantity*-1);
                    daoOf.addOffer(accion,codClient, stopLoss, null);
                }else{
                    accion.setQuantity(quantity);
                    daoOf.addOffer(accion,codClient, null, stopLoss);                    
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
    
    // CHECK SI ES UNA VENTA CON OFFERTA
    public boolean checkStopLoss(String codClient, String company,BigDecimal price){
        if(daoOf.traerOff(codClient,company,price) != null){
            return true; // TIENE OFERTA DE VENTA
        }else{
            return false;// NO TIENE OFERTA DE VENTA
        }            
    }
    
    public void checkOffers(ApiTrader apiTrader){
        List<Accion> sellOffers = daoOf.traerOfertasSell();//LISTA CON OFERTAS DE VENTA
        List<Accion> buyOffers = daoOf.traerOfertasBuy();//ISTA CON OFERTAS DE COMPRA
        for (Accion offer : sellOffers ) { 
            BigDecimal price = apiTrader.getPriceByCompany(offer.getCompany());
            if(price.equals(offer.getPrice())){
                 //SE VENDE ACCIONS
                 System.out.println("SE VENDE ACCION-------------------------");
                 System.out.println("\nCOMPANY"+offer.getCompany()+ "\nPRICE"+offer.getPrice()+
                 "\nQUANTITY"+offer.getQuantity()+ "\nCLIENTE"+offer.getCodCliente());
                 this.comprarAccion(offer.getCodCliente(), offer.getCompany(), offer.getPrice(), offer.getQuantity()*-1, null);
                  //SE ELIMINA LA OFERTA
                 daoOf.deleteOffer(offer.getIdOffer());
            }
            
        }
        for (Accion off : buyOffers ) {
            BigDecimal price = apiTrader.getPriceByCompany(off.getCompany());
            if(price.equals(off.getPrice())){
                 //SE COMPRA ACCION
                 System.out.println("SE COMPRA ACCIONN------------------------");
                 System.out.println("\nCOMPANY"+off.getCompany()+"\nPRICE"+off.getPrice()+"\nQUANTITY"+off.getQuantity()+"\nCLIENTE"+off.getCodCliente());
        
                 this.comprarAccion(off.getCodCliente(), off.getCompany(), off.getPrice(), off.getQuantity(), null);
                 //SE ELIMINA LA OFERTA
                 daoOf.deleteOffer(off.getIdOffer());
            }
        }
    }

    
}
