/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author adrian
 */
public class LoginModel {
    
    DBModel db;
    
    public LoginModel() throws ClassNotFoundException, SQLException {
        db = new DBModel();
    }
    
    public ResultSet auth(String email, String pass) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE email='" + email + "' "
                + "AND pass='" + pass + "';";
        ResultSet rs = db.result(query);
        return rs;
    }
}
