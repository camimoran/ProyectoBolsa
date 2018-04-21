/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author kamii
 */
public class Accion {

    private int id;
    private String company;
    private float price;
    private int quantity;
    
    public Accion(){
        id = 0;
        company = "";
        price = 0;
        quantity = 0;
    }
    
    public Accion(int i, String com, float pri, int qua){
        id = i;
        company = com;
        price = pri;
        quantity = qua;
    }

    public Accion(String company,float price,int quantity){
        this.company = company;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

}
