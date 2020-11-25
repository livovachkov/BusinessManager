/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnection;

import BusinessManager.Product;
import BusinessManager.Sales;
import BusinessManager.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Liliyan
 */
public class DbManager {

    String serverName = "localhost:3306";
    String schema = "users?zeroDateTimeBehavior=CONVERT_TO_NULL";
    
   public static void main(String[] args) throws Exception {
        DbManager db = new DbManager();
        
        db.addUser(new User("Kiril Ivanov Mihailov", "admin", "admin@admin.bg", "k_mihailov", "da22434F512!sfa24"));
        
        System.out.println("");
        
    }

    public DbManager() throws SQLException {
        String serverName = "localhost:3306";
        String schema = "users?zeroDateTimeBehavior=CONVERT_TO_NULL";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://" + serverName + "/" + schema, "root", "root"); Statement stmt = con.createStatement();) {
            String select = "select username from users";
            ResultSet rset = stmt.executeQuery(select);
            while (rset.next()) {
                System.out.println(rset.getString("username"));
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }
    

    
    
    public boolean addUser(User user) throws SQLException, Exception {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://" + serverName + "/" + schema, "root", "root");) {
            String query = "insert into users.users (ID, username, password, type, name, sales) values(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            if(!user.validateUser()) {
                JOptionPane.showMessageDialog(new JFrame(), "Invalid try for insertion into the registry! Invalid user!", "Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            ps.setInt(1, user.getId());
            ps.setString(2, user.getUser_name());
            ps.setString(3, PassSecurity.getSHA1(user.getPassword()));
            ps.setString(4, user.getPosition());
            ps.setString(5, user.getName());
            ps.setInt(6, user._getSales());
            ps.execute();
            /* if(rset.next()) {
                throw new SQLException("Impossible");
            }*/
        } catch (SQLException ex) {
            throw ex;
        }
        if (user == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid try for insertion! User is with empty characteristics!", "Alert", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Error, invalid product id! Cannot be fetched therefore returning null product!");
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /////GET DATA
    
    public User getUser(int id) throws SQLException, Exception {
        User user = null;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://" + serverName + "/" + schema, "root", "root");) {
            String query = "select * from users.users where id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, String.valueOf(id));
            ResultSet rset = ps.executeQuery();

            if (rset.next()) {
                String name = rset.getString("name"); //String name, String position, String email, String user_name, String password
                String position = rset.getString("position");
                String email = rset.getString("email");
                String user_name = rset.getString("user_name");
                String password = rset.getString("password"); 
                user = new User(name, position, email, user_name, password);
                user.setId(rset.getInt("id"));
            }
           /* if(rset.next()) {
                throw new SQLException("Impossible");
            }*/
        } catch (SQLException ex) {
            throw ex;
        }
        if(user == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid product id:" + id + "!", "Alert", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Error, invalid product id! Cannot be fetched therefore returning null product!");
        }
        return user;
    }
    

    public Sales getSale(int id) throws SQLException, Exception {
        Sales sale = null;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://" + serverName + "/" + schema, "root", "root");) {
            String query = "select * from users.sales where id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, String.valueOf(id));
            ResultSet rset = ps.executeQuery();

            if (rset.next()) {
                int _id = rset.getInt("id");
                Date date = rset.getDate("date");
                double price = rset.getDouble("price");
                Product product = getProduct(rset.getString("product"));
                sale = new Sales(_id, date, price, product);
            }
           /* if(rset.next()) {
                throw new SQLException("Impossible");
            }*/
        } catch (SQLException ex) {
            throw ex;
        }
        if(sale == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid product id:" + id + "!", "Alert", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Error, invalid product id! Cannot be fetched therefore returning null product!");
        }
        return sale;
    }

    public Product getProduct(int id) throws SQLException, Exception {
       Product prod = null;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://" + serverName + "/" + schema, "root", "root");) {
            String query = "select * from users.products where id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, String.valueOf(id));
            ResultSet rset = ps.executeQuery();

            if (rset.next()) {
                int _id = rset.getInt("id");
                double price = rset.getDouble("price");
                String manufacturer = rset.getString("manufacturer");
                String name = rset.getString("name");
                prod = new Product(String.valueOf(_id), name, manufacturer, price);
            }
           /* if(rset.next()) {
                throw new SQLException("Impossible");
            }*/
        } catch (SQLException ex) {
            throw ex;
        }
        if(prod == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid product id:" + id + "!", "Alert", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Error, invalid product id! Cannot be fetched therefore returning null product!");
        }
        return prod;
    }
    
   
    public Product getProduct(String id) throws SQLException, Exception {
       Product prod = null;
        try (Connection con = DriverManager.getConnection("jdbc:mysql://" + serverName + "/" + schema, "root", "root");) {
            String query = "select * from users.products where name = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, String.valueOf(id));
            ResultSet rset = ps.executeQuery();

            if (rset.next()) {
                int _id = rset.getInt("id");
                double price = rset.getDouble("price");
                String manufacturer = rset.getString("manufacturer");
                String name = rset.getString("name");
                prod = new Product(String.valueOf(_id), name, manufacturer, price);
            }
           /* if(rset.next()) {
                throw new SQLException("Impossible");
            }*/
        } catch (SQLException ex) {
            throw ex;
        }
        if(prod == null) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid product id:" + id + "!", "Alert", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Error, invalid product id! Cannot be fetched therefore returning null product!");
        }
        return prod;
    }
    
    
}
