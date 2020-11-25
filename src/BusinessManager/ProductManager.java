/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessManager;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Liliyan
 */
public class ProductManager {

    public ProductManager(User current_user) {
        this.current_user = current_user;
        if(!current_user.getPosition().equals("admin")) {
            JOptionPane.showConfirmDialog(new JFrame(), "Products can only be added from admin!", "Alert!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private User current_user;
    
    private ArrayList<Product> product = new ArrayList<Product>();
    
    public ArrayList<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product, boolean flag) {
        if(!current_user.getPosition().equals("admin") || flag == true) {
            JOptionPane.showConfirmDialog(new JFrame(), "Products can only be added from admin!", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.product = product;
    }

    public void addProduct(Product product) {
        if(!current_user.getPosition().equals("admin")) {
            JOptionPane.showConfirmDialog(new JFrame(), "Products can only be added from admin!", "Alert!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.product.add(product);
    }
}
