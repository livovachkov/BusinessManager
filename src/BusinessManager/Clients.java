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
public class Clients extends User {
    
    public Clients(String name, String position, String user_name, String password, String email) {
        super(name, position, user_name, password, email);
    }
    
    public Clients(User user) {
        super(user.getName(), user.getPosition(), user.getUser_name(),user.getPassword(), user.getEmail());
    }
    
}
