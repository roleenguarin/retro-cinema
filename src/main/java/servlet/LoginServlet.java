/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dbase.dbaseconnection;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Roleen
 */
public class LoginServlet extends HttpServlet {

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
            String useremail = request.getParameter("email");
            String password = request.getParameter("password");
            
            
            HttpSession session = request.getSession();
            session.setAttribute("email", useremail);
            session.setAttribute("justLoggedIn", false);
            String path = "Movie Posters" + File.separator;
            session.setAttribute("path", path);
            
          
            Connection conn = null;
            PreparedStatement pst = null;
     

            try {
                conn = dbaseconnection.getConnection();
                System.out.println(request.getParameter("email") + " " + request.getParameter("password"));


               String sql = "SELECT UserID, UserFName, UserType FROM user WHERE UserEmail = ? AND UserPassword = ?";
               pst = conn.prepareStatement(sql);
               pst.setString(1, useremail);
               pst.setString(2, password);

                //will receive the data from the executed select query
                ResultSet rs = pst.executeQuery();

                if(rs.next()) { //if acc exists
                    session.setAttribute("loginStatus", "Success");
                    
                    System.out.println(rs.getString("UserType") + " -- " + password);
                    System.out.println("Valid login");
                    
                    // LOG
                    pst = conn.prepareStatement("INSERT INTO actionlogs (UserID, ActionDone, WhenDone) values (?,'Login', NOW())");
                    pst.setInt(1, Integer.parseInt(rs.getString("UserID")));
                    int row = pst.executeUpdate();

                    if (row > 0)
                        System.out.println("Action Successfully Logged");
                    else
                        System.out.println("Error Logging Action");
                    
                    session.setAttribute("username", rs.getString("UserFName"));
                    session.setAttribute("userid", rs.getString("UserID"));
                    session.setAttribute("usertype", rs.getString("UserType"));
                    
                    if (rs.getString("UserType").equals("Admin"))
                        request.getRequestDispatcher("AdminServlet").forward(request, response);
                    else if (rs.getString("UserType").equals("Employee"))
                        request.getRequestDispatcher("AdminServlet").forward(request, response);
                    else
                        request.getRequestDispatcher("HomeServlet").forward(request, response);
                    
                    System.out.println(request.getSession(false) == null);
                }
                
                else{  //if acc does not exist
                    request.setAttribute("loggedin", false);
                    
                    request.setAttribute("inemail", useremail);
                    request.setAttribute("inpass", password);
                    
                    System.out.println(useremail + " -- " + password);
                    System.out.println("Invalid username/pass");
                    
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                }
        }catch (Exception e){
                e.printStackTrace();
                System.out.println("Error");
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