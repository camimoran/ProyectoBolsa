/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.math.BigDecimal;

/**
 *
 * @author kamii
 */
public class Accion {

    private int id;
    private String company;
    private BigDecimal price;
    private int quantity;
    
    public Accion(){
        id = 0;
        company = "";
        price = new BigDecimal(0); // CONVERTIR A BIG DECIMAL.
        quantity = 0;
    }
    
    public Accion(int i, String com, BigDecimal pri, int qua){
        id = i;
        company = com;
        price = pri; // CONVERTIR A BIG DECIMAL.
        quantity = qua;
    }

    public Accion(String company,BigDecimal price,int quantity){
        this.company = company;
        this.price = price; // CONVERTIR A BIG DECIMAL.
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

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
