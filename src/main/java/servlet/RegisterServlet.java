/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dbase.dbaseconnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Roleen
 */
@MultipartConfig
public class RegisterServlet extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String rgfirstname = request.getParameter("first");
            String rglastname = request.getParameter("last");
            String rgemail = request.getParameter("email");
            int rgcontact = Integer.parseInt(request.getParameter("contact").trim());
                System.out.println(request.getParameter("contact").trim());
            String rgpassword = request.getParameter("password");
            String rgrole = request.getParameter("role");
            
            Connection conn = null;
        
            try {
                //INSERT
                String sq = "INSERT INTO user (UserEmail, UserFName, UserLName, UserContact, UserPassword, UserType) VALUES (?,?,?,?,?,?)";
                conn = dbaseconnection.getConnection();
                PreparedStatement pst = conn.prepareStatement(sq);
                
                pst.setString(1, rgfirstname);
                pst.setString(2, rglastname);
                pst.setString(3, rgemail);
                pst.setInt(4, rgcontact);
                pst.setString(5, rgpassword);
                pst.setString(6, rgrole);

                int row = pst.executeUpdate();

                if (row > 0)
                    System.out.println("Account Successfully Created");
                else
                    System.out.println("Query Error");
                
                request.getRequestDispatcher("HomeServlet").forward(request, response);
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Error");
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
        processRequest(request, response);
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
        processRequest(request, response);
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

}