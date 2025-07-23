/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet.admin;

import dbase.dbaseconnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.DBFunctions;

/**
 *
 * @author Roleen
 */
public class UpdateUsersServlet extends HttpServlet {

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
        try {
                //variables
                Connection conn = dbaseconnection.getConnection();
                PreparedStatement pst_rg = null;
                String sq_rg, sqd_rg;

                //table constants
                String table = "User";
                String idvar = "UserID";
                String recordID = request.getParameter("recordID"); //BookingID
                System.out.println(recordID);

                //input parameters
                String email = request.getParameter("email");
                String fname = request.getParameter("fname");
                String lname = request.getParameter("lname");
                String contact = request.getParameter("contact");
                String type = request.getParameter("type"); //dropdown
                
                //update record
                if(!email.equals(""))
                    DBFunctions.update(table,"UserEmail",email,idvar,recordID);
                if(!fname.equals(""))
                    DBFunctions.update(table,"UserEmail",fname,idvar,recordID);
                if(!lname.equals(""))
                    DBFunctions.update(table,"UserEmail",lname,idvar,recordID);
                if(!contact.equals("")){
                    String updateString = "UPDATE "+table+" SET UserContact = "+Integer.parseInt(contact)+" WHERE "+idvar+" = "+recordID;
                    pst_rg = conn.prepareStatement(updateString);
                    pst_rg.executeUpdate();
                    System.out.println(table + ": UserContact Updated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }
                if(!type.equals(""))
                    DBFunctions.update(table,"UserType",type,idvar,recordID);
                
                //back to main page
                request.getRequestDispatcher("AdminUsersServlet").forward(request, response);
                
            }  catch (Exception e){ e.printStackTrace(); }
        
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
