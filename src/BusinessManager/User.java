/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Liliyan
 */
public class User {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPasswordHash() {
        return String.valueOf(this.password.hashCode());
    }

    public User(String name, String position, String email, String user_name, String password) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.user_name = user_name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private String user_name, password;
    private String name, position, email;
    private int id, sales;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public boolean validatePosition() {
        return(position.equals("user") ||  position.equals("admin") || position.equals("representative"));
    }

    public boolean isValidUsername() {
        Pattern p = Pattern.compile("^[A-Za-z]\\w{5,29}$");
        if(user_name == null)
            return false;
        Matcher m = p.matcher(user_name);
        return m.matches();
    }
    
    public boolean isValidPassword() {
        if (password.length() > 15 || password.length() < 8) {
            JOptionPane.showMessageDialog(new JFrame(),"Password must be less than 20 and more than 8 characters in length!", "Alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
               
        Pattern p = Pattern.compile("^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$");
        if(password == null)
            return false;
        Matcher m = p.matcher(password);
        return m.matches();
    }
    
    public boolean isValidName() {
        if(name == null)
            return false;
        Pattern p = Pattern.compile("^[\\\\p{L} .'-]+$");
        Matcher m = p.matcher(name);
        return m.matches();
    }
    
    public boolean isValidEmail() {
        if(email == null)
            return false;
        Pattern p = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        Matcher m = p.matcher(email);
        return m.matches();
    }
    

    public boolean validateUser() {
        return (validatePosition() && isValidUsername() && isValidPassword() && isValidName() && isValidEmail());
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
    
    public int getSales() {
        return sales;
    }
    
}
