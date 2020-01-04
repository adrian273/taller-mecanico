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
public class AutomovilModel {
    
    DBModel db;
    
    public AutomovilModel() throws ClassNotFoundException, SQLException {
        db = new DBModel();
    }
    
    public ResultSet getVehiculos() throws SQLException {
        String query = "SELECT vh.*, tp.nombre as nombre FROM vehiculos vh, tipos tp "
                + "WHERE vh.tipos_id = tp.id ORDER BY vh.id DESC";
        ResultSet rs = db.result(query);
        return rs;
    }
    
    public ResultSet getVehiculosById(int id) throws SQLException {
        String query = "SELECT vh.*, tp.nombre as nombre FROM vehiculos vh, tipos tp "
                + "WHERE vh.tipos_id = tp.id AND vh.id=" + id;
        ResultSet rs = db.result(query);
        return rs;
    }
    
    public boolean insert(String tipo, int num_chasis, 
            String marca, String modelo, String color, String patente) throws SQLException {
        String query = "INSERT INTO vehiculos(tipos_id, num_chasis, marca, modelo, color, patente) "
                + "VALUES ("+ Integer.parseInt(String.valueOf(tipo)) + ", "
                + "" + num_chasis + ", "
                + "'" + marca + "'" + ", "
                + "'" + modelo + "', "
                + "'" + color + "', "
                + "'" + patente.toUpperCase()+ "');" ;
        System.out.println(query);
        return db.execute(query);
    }
    
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM vehiculos WHERE id=" + id;
        return db.execute(query);
    }
    
    public boolean update(int id_v, String tipo_up, int num_chasis_up, 
            String marca_up, String modelo_up, String color_up) throws SQLException {
        String query = "UPDATE vehiculos SET tipos_id=" + Integer.parseInt(String.valueOf(tipo_up)) + ", "
                + "num_chasis=" + num_chasis_up + ", "
                + "marca='" + marca_up + "', "
                + "modelo='" + modelo_up + "', "
                + "marca='" + marca_up + "', "
                + "color='" + color_up + "' "
                + "WHERE id=" + id_v + ";";
        System.out.println(query);
        return db.execute(query);
    }
    
    public ResultSet verificarPatente(String patente) throws SQLException {
        String query = "SELECT id, patente FROM vehiculos WHERE patente='" + patente.toUpperCase() + "';";
        ResultSet rs = db.result(query);
        return rs;
    }
    
    public ResultSet getAutoLog(int id) throws SQLException {
        String query = "SELECT rp.*, vh.patente as patente, tp.nombre as nombre "
                + "FROM vehiculos vh, tipos tp, reparaciones rp "
                + "WHERE vh.id=rp.vehiculos_id AND rp.tipos_id=tp.id "
                + "AND vh.id=" + id 
                + " ORDER BY fecha DESC, rp.id ASC";
        ResultSet rs = db.result(query);
        return rs;
    }
}
