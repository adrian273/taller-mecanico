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
public class ReparacionModel {
    
    DBModel db;
    
    public ReparacionModel() throws ClassNotFoundException, SQLException {
        db = new DBModel();
    }
    
    public ResultSet getAll() throws SQLException {
        String query = "SELECT rp.*, vh.patente as patente, tp.nombre as nombre "
                + "FROM vehiculos vh, tipos tp, reparaciones rp "
                + "WHERE vh.id=rp.vehiculos_id AND rp.tipos_id=tp.id ORDER BY fecha DESC, rp.id ASC";
        ResultSet rs = db.result(query);
        return rs;
    }
    
    public boolean insert(String fecha, int tipos_id, String descripcion, 
            int costo, int vehiculos_id) throws SQLException {
        String query = "INSERT INTO reparaciones(fecha, tipos_id, descripcion, costo, vehiculos_id) "
                + "VALUES('" + fecha + "', "
                + "" + tipos_id + ", "
                + "'" + descripcion + "', "
                + "" + costo + ", "
                + "" + vehiculos_id + ");";
        System.out.println(query);
        return db.execute(query);
    }
    
    public ResultSet getById(int id) throws SQLException {
        String query = "SELECT rp.*, vh.patente as patente, tp.nombre as nombre "
                + "FROM vehiculos vh, tipos tp, reparaciones rp "
                + "WHERE vh.id=rp.vehiculos_id AND rp.tipos_id=tp.id AND rp.id=" + id
                + " ORDER BY fecha DESC, rp.id ASC";
        ResultSet rs = db.result(query);
        return rs;
    }
    
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM reparaciones WHERE id=" + id;
        return db.execute(query);
    }
    
    public boolean update(int id_log, 
            int tipos_id_up, String fecha_up, String descripcion_up, int costo_up) throws SQLException{
        String query = "UPDATE reparaciones SET fecha='" + fecha_up + "', "
                + "tipos_id=" + tipos_id_up + ", "
                + "descripcion='" + descripcion_up + "', "
                + "costo=" + costo_up + " WHERE id=" + id_log + ";";
        System.out.println(query);
        return db.execute(query);
    }
    
}
