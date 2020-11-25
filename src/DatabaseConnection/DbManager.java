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
        
        Product prod = db.getProduct(3);
        
        System.out.println("Product retrieved with id " + 3 + " " + prod.getProduct_name());
        
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
    
    

    private String generateActualSql(String sqlQuery, Object... parameters) {
        String[] parts = sqlQuery.split("\\?");
        StringBuilder sb = new StringBuilder();

        // This might be wrong if some '?' are used as litteral '?'
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            sb.append(part);
            if (i < parameters.length) {
                sb.append(formatParameter(parameters[i]));
            }
        }

        return sb.toString();
    }

    private String formatParameter(Object parameter) {
        if (parameter == null) {
            return "NULL";
        } else {
            if (parameter instanceof String) {
                return "'" + ((String) parameter).replace("'", "''") + "'";
            } else if (parameter instanceof Timestamp) {
                return "to_timestamp('" + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS").
                        format(parameter) + "', 'mm/dd/yyyy hh24:mi:ss.ff3')";
            } else if (parameter instanceof Date) {
                return "to_date('" + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").
                        format(parameter) + "', 'mm/dd/yyyy hh24:mi:ss')";
            } else if (parameter instanceof Boolean) {
                return ((Boolean) parameter).booleanValue() ? "1" : "0";
            } else {
                return parameter.toString();
            }
        }
    }
}
