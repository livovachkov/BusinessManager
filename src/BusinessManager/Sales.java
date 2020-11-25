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
        this.sales = sales;
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
    
    private Date dateOfSale;
    private double sales;
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    
    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
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
        return (isValidDate() && product.isValidProduct() && sales >= 0);
    }
    
}
