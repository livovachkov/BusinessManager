/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessManager;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 *
 * @author Liliyan
 */
public class Sales {

    public Sales(double sales) {
        this.price = sales;
    }

    public Date getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(Date dateOfSale) {
        this.dateOfSale = dateOfSale;
    }
    
    public void setDateOfSale(int year, int month, int date, int hrs, int min, int sec) {
        this.dateOfSale = new Date(year, month, date, hrs, min, sec);
    }

    public Sales(int id, Date dateOfSale, double price, Product product) {
        this.dateOfSale = dateOfSale;
        this.price = price;
        this.product = product;
    }
    
    private Date dateOfSale;
    private double price;
    private Product product;
    private int id;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //private User representative;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public boolean isValidDate() {
        try {
            LocalDate.parse(dateOfSale.toString());
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
    
    public boolean checkIntegrity() {
        return (isValidDate() && product.isValidProduct() && price >= 0 && id >= 0/* && representative.getPosition().equals("representative")*/);
    }
    
}
