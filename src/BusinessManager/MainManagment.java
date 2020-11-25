/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessManager;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

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

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        if (currentUser.getPosition().equals("admin")) {
            this.users = users;
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "User from type " + currentUser.getPosition() + " cannot add users!", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void addUser(String name, String position, String email, String user_name, String password) {
        try {
            User user = new User(name, position, email, user_name, password);
            if (!user.validatePosition()) {
                JOptionPane.showMessageDialog(new JFrame(), "Invalid user type! Cannot continue!", "Alert", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (currentUser.getPosition().equals("admin") && (currentUser instanceof Administrator) /* && (user.getPosition().equals("representative") || user.getPosition().equals("admin") || user.getPosition().equals("user"))*/) {
                user = registerUser(user);
            } else if (currentUser.getPosition().equals("representative") && (currentUser instanceof SalesRepresentative) && user.getPosition().equals("user")) {
                user = registerUser(user);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "User from type " + currentUser.getPosition() + " cannot add " + user.getPosition() + "!", "Alert", JOptionPane.WARNING_MESSAGE);
                return;
            }
            users.add(user);
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Unknown exception! Please try again!", "Alert", JOptionPane.WARNING_MESSAGE);
            throw ex;
        }
    }
    
    public User registerUser(User user) {
        while (!user.validateUser()) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid data for user!", "Alert", JOptionPane.WARNING_MESSAGE);
            user.setName(JOptionPane.showInputDialog("Please enter a valid name: "));
            user.setEmail(JOptionPane.showInputDialog("Please enter a valid email: "));
            user.setUser_name(JOptionPane.showInputDialog("Please enter a valid user name: "));
            JPasswordField pf = new JPasswordField();
            int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (okCxl == JOptionPane.OK_OPTION) {
                String pass = new String(pf.getPassword());
                user.setPassword(pass);
            }
            user.setName(JOptionPane.showInputDialog("Please enter a valid name: "));
        }
        return user;

    }

    public void removeUser(int id) {
        try {
            if (users.get(id).getPosition().equals("admin") && (currentUser instanceof Administrator)) {
                if (currentUser.getPosition().equals("admin") && (currentUser instanceof Administrator)) {
                    int admins = 0;
                    for (User us : users) {
                        if (us.getPosition().equals("admin") && (us instanceof Administrator)) {
                            admins++;
                        }
                    }
                    if (!(admins >= 2)) {
                        JOptionPane.showMessageDialog(new JFrame(), "Cannnot delete admin user as it is the only admin!", "Alert", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "User from type " + currentUser.getPosition() + " cannot delete admin!", "Alert", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            } else if ((users.get(id).getPosition().equals("representative") || users.get(id).getPosition().equals("user")) && (currentUser instanceof Administrator)) {

            } else if (users.get(id).getPosition().equals("user") && ((currentUser instanceof SalesRepresentative) || currentUser instanceof Administrator)) {

            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Cannnot delete " + users.get(id).getPosition() + " as it can only be deleted from administrator!", "Alert", JOptionPane.WARNING_MESSAGE);
                return;
            }
            users.remove(id);
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Invalid user id as it exceeds the size of the current users!", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void addUserToDb() { // TODO ADMIN ONLY, HASH PASSWORD // NOTE: MAYBE ANOTHER CLASS WITH DB ONLY

    }

    public void removeUserToDb() { // TODO ADMIN ONLY, HASH PASSWORD

    }

    private String connectionUrl;
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Product> product = new ArrayList<Product>();


    public ArrayList<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product) {
        this.product = product;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    private User currentUser;

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
