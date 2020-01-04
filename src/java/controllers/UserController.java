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
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.UserModel;

/**
 *
 * @author adrian
 */
public class UserController extends HttpServlet {

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
                case "store":
                    String nombre = request.getParameter("nombre");
                    String email = request.getParameter("email");
                    String pass = request.getParameter("pass");
                    insert(response, nombre, email, pass);
                    break;
                case "user-data":
                    getAll(response);
                    break;
                case "one-data":
                    String idGet = request.getParameter("id");
                    getById(response, idGet);
                    break;
                case "delete":
                    String idDelete = request.getParameter("id");
                    delete(response, idDelete);
                    break;
                case "update":
                    String id_up = request.getParameter("id_user");
                    String nombre_up = request.getParameter("nombre");
                    String email_up = request.getParameter("email");
                    String pass_up = request.getParameter("pass");
                    update(response, id_up, nombre_up, email_up, pass_up);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void insert(HttpServletResponse response, String nombre, String email, String pass) throws IOException, ClassNotFoundException, SQLException {
        try (PrintWriter out = response.getWriter()) {
            UserModel usermdl = new UserModel();
            boolean in = usermdl.insert(nombre, email, pass);
            JsonObjectBuilder obj = Json.createObjectBuilder();
            if (in == false) {
                obj.add("type", "success");
            } else {
                obj.add("type", "error");
            }
            out.print(obj.build());
        }
    }

    private void getAll(HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException {
        try (PrintWriter out = response.getWriter()) {
            JsonObjectBuilder obj = Json.createObjectBuilder();
            JsonArrayBuilder arrObj = Json.createArrayBuilder();
            UserModel user = new UserModel();
            ResultSet rs = user.getAll();
            
            while (rs.next()) {
                obj.add("nombre", rs.getString("nombre"));
                obj.add("id", rs.getString("id"));
                obj.add("email", rs.getString("email"));
                obj.add("pass", rs.getString("pass"));
                arrObj.add(obj);
            }
            
            out.print(arrObj.build());
        }
    }

    private void getById(HttpServletResponse response, String idGet) throws SQLException, ClassNotFoundException, IOException {
        try (PrintWriter out = response.getWriter()) {
            JsonObjectBuilder obj = Json.createObjectBuilder();
            UserModel user = new UserModel();
            ResultSet rs = user.getById(Integer.parseInt(String.valueOf(idGet)));            
            while (rs.next()) {
                obj.add("nombre", rs.getString("nombre"));
                obj.add("id", rs.getString("id"));
                obj.add("email", rs.getString("email"));
                obj.add("pass", rs.getString("pass"));
            }
            out.print(obj.build());
        }
    }

    private void delete(HttpServletResponse response, String idDelete) throws IOException, ClassNotFoundException, SQLException {
        try (PrintWriter out = response.getWriter()) {
            JsonObjectBuilder obj = Json.createObjectBuilder();
            UserModel user = new UserModel();
            boolean del = user.delete(Integer.parseInt(String.valueOf(idDelete)));
            if (del == false) {
                obj.add("type", "success");
            } else {
                obj.add("type", "error");
            }
            out.print(obj.build());
        }
    }

    private void update(HttpServletResponse response, String id, String nombre_up, String email_up, String pass_up) 
            throws IOException, ClassNotFoundException, SQLException {
         try (PrintWriter out = response.getWriter()) {
            JsonObjectBuilder obj = Json.createObjectBuilder();
            UserModel user = new UserModel();
            boolean up = user.update(Integer.parseInt(String.valueOf(id)), nombre_up, email_up, pass_up);
            if (up == false) {
                obj.add("type", "success");
                obj.add("nombre", nombre_up);
                obj.add("id", id);
                obj.add("email", email_up);
                obj.add("pass", pass_up);
            } else {
                obj.add("type", "error");
            }
            out.print(obj.build());
        }   
    }

}
