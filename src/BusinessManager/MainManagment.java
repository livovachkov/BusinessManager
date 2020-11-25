/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessManager;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Liliyan
 */
public class MainManagment {

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public void addUserToDb() { // TODO ADMIN ONLY, HASH PASSWORD // NOTE: MAYBE ANOTHER CLASS WITH DB ONLY

    }

    public void removeUserToDb() { // TODO ADMIN ONLY, HASH PASSWORD

    }

    private String connectionUrl;
    private User current_user;
    private ClientManager clients = new ClientManager(current_user); // should get it from login!
    private ProductManager products = new ProductManager(current_user);

    public void connectToDatabase() {
        try (Connection connection = DriverManager.getConnection(connectionUrl);) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeQuery() {
        try (Connection connection = DriverManager.getConnection(connectionUrl);) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
