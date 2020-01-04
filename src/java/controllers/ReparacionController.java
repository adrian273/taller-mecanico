/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ReparacionModel;

/**
 *
 * @author adrian
 */
public class ReparacionController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            switch (action) {
                case "get-all":
                    getAll(response);
                    break;
                case "store":
                    int tipos_id = Integer.parseInt(String.valueOf(request.getParameter("tipo")));
                    String fecha = request.getParameter("fecha");
                    String descripcion = request.getParameter("descripcion");
                    int costo = Integer.parseInt(String.valueOf(request.getParameter("costo")));
                    int vehiculos_id = Integer.parseInt(String.valueOf(request.getParameter("id")));
                    store(response, request, fecha, tipos_id, descripcion, costo, vehiculos_id);
                    break;
                case "one-data":
                    int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
                    getById(response, id);
                    break;
                case "delete":
                    int i = Integer.parseInt(String.valueOf(request.getParameter("id")));
                    delete(response, i);
                    break;
                case "update":
                    int id_log = Integer.parseInt(String.valueOf(request.getParameter("id_log")));
                    int tipos_id_up = Integer.parseInt(String.valueOf(request.getParameter("tipo")));
                    String fecha_up = request.getParameter("fecha");
                    String descripcion_up = request.getParameter("descripcion");
                    int costo_up = Integer.parseInt(String.valueOf(request.getParameter("costo")));
                    update(response, id_log, tipos_id_up, fecha_up, descripcion_up, costo_up);
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReparacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ReparacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * 
     * @param response
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    private void getAll(HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
        try (PrintWriter out = response.getWriter()) {
            JsonObjectBuilder obj = Json.createObjectBuilder();
            ReparacionModel rpmdl = new ReparacionModel();
            ResultSet rs = rpmdl.getAll();
            JsonArrayBuilder arrObj = Json.createArrayBuilder();
            while (rs.next()) {
                obj.add("id", rs.getString("id"));
                obj.add("fecha", rs.getString("fecha"));
                obj.add("tipo", rs.getString("nombre"));
                obj.add("descripcion", rs.getString("descripcion"));
                obj.add("costo", rs.getString("costo"));
                obj.add("patente", rs.getString("patente"));
                arrObj.add(obj);
            }
            out.print(arrObj.build());
        }
    }

    private void store(HttpServletResponse response, HttpServletRequest request, 
            String fecha, int tipos_id, String descripcion, 
            int costo, int vehiculos_id) throws IOException, ClassNotFoundException, SQLException {
        try (PrintWriter out = response.getWriter()) {
            JsonObjectBuilder obj = Json.createObjectBuilder();
            ReparacionModel rpcmdl = new ReparacionModel();
            boolean ins = rpcmdl.insert(fecha, tipos_id, descripcion, costo, vehiculos_id);
            if (ins == false) {
                obj.add("type", "success");
            } else {
                obj.add("type", "error");
            }
            out.print(obj.build());
        }
    }

    private void getById(HttpServletResponse response, int id) throws IOException, ClassNotFoundException, SQLException {
        try (PrintWriter out = response.getWriter()) {
            ReparacionModel rpmdl = new ReparacionModel();
            JsonObjectBuilder obj = Json.createObjectBuilder();
            ResultSet rs = rpmdl.getById(id);
            if (rs.first()) {
                obj.add("type", "success");
                obj.add("id", rs.getString("id"));
                obj.add("fecha", rs.getString("fecha"));
                obj.add("tipo", rs.getString("nombre"));
                obj.add("descripcion", rs.getString("descripcion"));
                obj.add("costo", rs.getString("costo"));
                obj.add("patente", rs.getString("patente")); 
            } else {
                obj.add("type", "error");
            }
            out.print(obj.build());
        }
    }

    private void delete(HttpServletResponse response, int i) throws IOException, ClassNotFoundException, SQLException {
        try (PrintWriter out = response.getWriter()) {
            JsonObjectBuilder obj = Json.createObjectBuilder();
            ReparacionModel rp = new ReparacionModel();
            boolean del = rp.delete(i);
            if (del == false) {
                obj.add("type", "success");
            }
            else {
                obj.add("type", "success");
            }
            out.print(obj.build());
        }
    }

    private void update(HttpServletResponse response, int id_log, 
            int tipos_id_up, String fecha_up, String descripcion_up, int costo_up) 
            throws IOException, ClassNotFoundException, SQLException {
        try (PrintWriter out = response.getWriter()) {
            ReparacionModel rp = new ReparacionModel();
            boolean up = rp.update(id_log, tipos_id_up, fecha_up, descripcion_up, costo_up);
            JsonObjectBuilder obj = Json.createObjectBuilder();
            if (up == false) {
                obj.add("type", "success");
                obj.add("fecha", fecha_up);
                obj.add("descripcion", descripcion_up);
                obj.add("costo", costo_up);
                obj.add("tipo", tipos_id_up);
            } else {
                obj.add("type", "error");
            }
            out.print(obj.build());
        }
    }

}
