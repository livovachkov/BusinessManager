/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessManager;

/**
 *
 * @author Liliyan
 */
public class Product {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product(String id, String product_name, String manufacturer, double price) {
        this.id = id;
        this.product_name = product_name;
        this.manufacturer = manufacturer;
        this.price = price;
    }
    private String id, product_name, manufacturer;
    private double price;
    
    public boolean isValidProduct() {
        return (price >= 0 && Integer.getInteger(id) >= 0 && product_name != null && manufacturer != null);
    }
    
}
