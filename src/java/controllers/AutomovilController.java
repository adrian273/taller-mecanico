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
import models.AutomovilModel;

/**
 *
 * @author adrian
 */
public class AutomovilController extends HttpServlet {

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
                case "automovil-data":
                    getAutomovil(request, response);
                    break;
                case "one-data":
                    int id = Integer.parseInt(String.valueOf(request.getParameter("id")));
                    getAutomovilById(request, response, id);
                case "addAuto":
                    String tipo = request.getParameter("tipo");
                    int num_chasis = Integer.parseInt(String.valueOf(request.getParameter("num_chasis")));
                    String marca = request.getParameter("marca");
                    String modelo = request.getParameter("modelo");
                    String color = request.getParameter("color");
                    String patente = request.getParameter("patente");
                    addNewAutomovil(request, response, tipo, num_chasis, marca, modelo, color, patente);
                    break;
                case "delete":
                    int i = Integer.parseInt(String.valueOf(request.getParameter("id")));
                    deleteAutomovil(request, response, i);
                case "update":
                    int id_v = Integer.parseInt(String.valueOf(request.getParameter("id_auto")));
                    String tipo_up = request.getParameter("tipo");
                    int num_chasis_up = Integer.parseInt(String.valueOf(request.getParameter("num_chasis")));
                    String marca_up = request.getParameter("marca");
                    String modelo_up = request.getParameter("modelo");
                    String color_up = request.getParameter("color");
                    updateAutomovil(request, response, id_v, tipo_up, num_chasis_up, marca_up, modelo_up, color_up);
                    break;
                case "verificarPatente":
                    String patente_v = request.getParameter("patente");
                    veficarPatente(request, response, patente_v);
                case "get-patente":
                    String pat = request.getParameter("patente");
                    getPatente(request, response, pat);
                    break;
                case "get-log-auto":
                    int id_auto = Integer.parseInt(String.valueOf(request.getParameter("id")));
                    getLogAuto(response, id_auto);
                    break;
            }
        }
    }

    protected void getAutomovil(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            AutomovilModel automdl = new AutomovilModel();
            ResultSet rs = automdl.getVehiculos();
            JsonArrayBuilder jsonArr = Json.createArrayBuilder();
            JsonObjectBuilder obj = Json.createObjectBuilder();
            while (rs.next()) {
                obj.add("id", rs.getString("id"));
                obj.add("tipos", rs.getString("nombre"));
                obj.add("num_chasis", rs.getString("num_chasis"));
                obj.add("marca", rs.getString("marca"));
                obj.add("modelo", rs.getString("modelo"));
                obj.add("color", rs.getString("color"));
                obj.add("patente", rs.getString("patente"));
                jsonArr.add(obj);
            }
            out.print(jsonArr.build());
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AutomovilController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AutomovilController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AutomovilController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AutomovilController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void getAutomovilById(HttpServletRequest request, HttpServletResponse response, int id)
            throws ClassNotFoundException, SQLException, IOException {
        AutomovilModel automdl = new AutomovilModel();
        ResultSet rs = automdl.getVehiculosById(id);
        JsonObjectBuilder obj = Json.createObjectBuilder();
        while (rs.next()) {
            obj.add("id", rs.getString("id"));
            obj.add("tipos", rs.getString("nombre"));
            obj.add("num_chasis", rs.getString("num_chasis"));
            obj.add("marca", rs.getString("marca"));
            obj.add("modelo", rs.getString("modelo"));
            obj.add("color", rs.getString("color"));
            obj.add("patente", rs.getString("patente"));
        }
        try (PrintWriter out = response.getWriter()) {
            out.print(obj.build());
        }
    }

    private void addNewAutomovil(HttpServletRequest request, HttpServletResponse response,
            String tipo, int num_chasis, String marca, String modelo, String color, String patente) throws IOException, ClassNotFoundException, SQLException {
        JsonObjectBuilder json = Json.createObjectBuilder();
        AutomovilModel automdl = new AutomovilModel();
        try (PrintWriter out = response.getWriter()) {
            boolean insert = automdl.insert(tipo, num_chasis, marca, modelo, color, patente);
            if (insert == false) {
                json.add("type", "success");
            } else {
                json.add("type", "error");
            }
            out.print(json.build());
        }
    }

    /**
     *
     * @param request
     * @param response
     * @param i
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void deleteAutomovil(HttpServletRequest request, HttpServletResponse response, int i) throws IOException, ClassNotFoundException, SQLException {
        try (PrintWriter out = response.getWriter()) {
            JsonObjectBuilder json = Json.createObjectBuilder();
            AutomovilModel amdl = new AutomovilModel();
            boolean del = amdl.delete(i);
            if (del == false) {
                json.add("type", "success");
            } else {
                json.add("type", "error");
            }
            out.print(json.build());
        }
    }

    private void updateAutomovil(HttpServletRequest request, HttpServletResponse response,
            int id_v, String tipo_up, int num_chasis_up,
            String marca_up, String modelo_up, String color_up) throws IOException, ClassNotFoundException, SQLException {
        try (PrintWriter out = response.getWriter()) {
            AutomovilModel automdl = new AutomovilModel();
            JsonObjectBuilder json = Json.createObjectBuilder();
            boolean up = automdl.update(id_v, tipo_up, num_chasis_up, marca_up, modelo_up, color_up);
            if (up == false) {
                json.add("type", "success");
                json.add("tipo", tipo_up);
                json.add("num_chasis", num_chasis_up);
                json.add("marca", marca_up);
                json.add("modelo", modelo_up);
                json.add("color", color_up);
            } else {
                json.add("type", "error");
            }
            out.print(json.build());
        }
    }

    private void veficarPatente(HttpServletRequest request, HttpServletResponse response, String patente_v) throws IOException, ClassNotFoundException, SQLException {
        try (PrintWriter out = response.getWriter()) {
            JsonObjectBuilder obj = Json.createObjectBuilder();
            AutomovilModel automdl = new AutomovilModel();
            ResultSet rs = automdl.verificarPatente(patente_v);

            if (rs.next()) {
                obj.add("type", "no-disponible");
            } else {
                obj.add("type", "disponible");
            }

            out.print(obj.build());
        }
    }

    private void getPatente(HttpServletRequest request, HttpServletResponse response, String pat) throws IOException, ClassNotFoundException, SQLException {
        try (PrintWriter out = response.getWriter()) {
            AutomovilModel automdl = new AutomovilModel();
            ResultSet rs = automdl.verificarPatente(pat);
            JsonObjectBuilder obj = Json.createObjectBuilder();
            if (rs.next()) {
                obj.add("type", "success");
                obj.add("id", rs.getString("id"));
                obj.add("patente", rs.getString("patente"));
            } else {
                obj.add("type", "error");
            }
            out.print(obj.build());
        }
    }

    private void getLogAuto(HttpServletResponse response, int id) throws IOException, ClassNotFoundException, SQLException {
        try (PrintWriter out = response.getWriter()) {
            JsonObjectBuilder obj = Json.createObjectBuilder();
            JsonArrayBuilder arrObj = Json.createArrayBuilder();
            AutomovilModel amdl = new AutomovilModel();
            ResultSet rs = amdl.getAutoLog(id);
            while (rs.next()) {
                obj.add("id", rs.getString("id"));
                obj.add("fecha", rs.getString("fecha"));
                obj.add("tipo", rs.getString("nombre"));
                obj.add("descripcion", rs.getString("descripcion"));
                obj.add("costo", rs.getString("costo"));
                arrObj.add(obj);
            }
            out.print(arrObj.build());
        }
    }

}
