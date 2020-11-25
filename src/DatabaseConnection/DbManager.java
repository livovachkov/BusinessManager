/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseConnection;

import BusinessManager.Sales;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Liliyan
 */
public class DbManager {
    
    public DbManager() throws SQLException {
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "root"); Statement stmt = con.createStatement();) {
            String select = "select username from users";
            ResultSet rset = stmt.executeQuery(select);
            while(rset.next()) {
                System.out.println(rset.getString("username"));
            }
        }catch(SQLException ex) {
            throw ex;
        }
    }
    
    public Sales getSale(int id) throws SQLException {
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "root"); Statement stmt = con.createStatement();) {
            String select = "select sales from users where id = " + String.valueOf(id);
            ResultSet rset = stmt.executeQuery(select);
            while(rset.next()) {
                int _id = rset.getInt("id");
            }
        }catch(SQLException ex) {
            throw ex;
        } 
        return null;
    }
}
