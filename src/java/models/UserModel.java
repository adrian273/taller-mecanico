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
public class UserModel {
    
    DBModel db;
    
    public UserModel() throws ClassNotFoundException, SQLException {
        db = new DBModel();
    }
    
    public boolean insert(String nombre, String email, String pass) throws SQLException {
        String query = "INSERT INTO usuarios (email, pass, nombre) "
                + "VALUES ('" + email + "', "
                + "'" + pass + "', "
                + "'" + nombre + "');";
        System.out.println(query);
        return db.execute(query);
    }
    
    public ResultSet getAll() throws SQLException {
        String query = "SELECT * FROM usuarios";
        ResultSet rs = db.result(query);
        return rs;
    }
    
    public ResultSet getById(int id) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE id=" + id;
        ResultSet rs = db.result(query);
        return rs;
    }

    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM usuarios WHERE id=" + id;
        return db.execute(query);
    }
    
    public boolean update(int id, String nombre, String email, String pass) throws SQLException {
        String query = "UPDATE usuarios SET nombre='" + nombre + "', "
                + "email='" + email + "', "
                + "pass='" + pass + "' WHERE id=" + id;
        System.out.println(query);
        return db.execute(query);
    }
    
}
