/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessManager;

import java.awt.HeadlessException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Liliyan
 */
public class SalesRepresentative extends User {

    public ArrayList<Sales> getSale() {
        return sales;
    }
    
    public double getSales() {
        double totalSales = 0;
        for(Sales s: sales) {
            totalSales += s.getSales();
        }
        return totalSales;
    }

    public boolean addSales(Sales sales) {
        try {
            if(sales.checkIntegrity()) {
                this.sales.add(sales);
            } else  {
                JOptionPane.showMessageDialog(new JFrame(),"Invalid sale data is trying to be thrown into the structure!", "Alert",JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch(HeadlessException ex) {
            throw ex;
        }
        return true;
    }
    
    public void removeSales(int id) throws Exception {
        try {
            if(sales.size() < id) {
                JOptionPane.showMessageDialog(new JFrame(),"Given id " + id + " exceeds sales table size " + sales.size(), "Alert",JOptionPane.WARNING_MESSAGE);  
                throw new Exception("Invalid id for sales!");
            }
            else {
                sales.remove(id);
            }
        } catch(Exception ex) {
            throw ex;
        }
    }
    
    private final ArrayList<Sales> sales = new ArrayList<Sales>();

    
    public SalesRepresentative(String name, String position, String user_name, String password, String email) {
        super(name, position, user_name, password, email);
    }
    
    public SalesRepresentative(User user) {
        super(user.getName(), user.getPosition(), user.getUser_name(),user.getPassword(), user.getEmail());
    }
    
    
}
