package servlet.admin;

import dbase.dbaseconnection;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Roleen
 */
public class AdminUsersServlet extends HttpServlet {

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
            try {
                //variables
                Connection conn = dbaseconnection.getConnection();
                PreparedStatement pst_rg = null;
                String sq_rg;
                
                /*String searchInput_rg = request.getParameter("search");
                
                if(searchInput_rg != null  && !searchInput_rg.trim().isEmpty()){
                    sq_rg = "SELECT * FROM TicketPurchase WHERE TicketSchedule LIKE ? OR PaymentMethod LIKE ? OR User LIKE ?";
                    String searchFormat_rg = "%" + searchInput_rg + "%";
                    pst_rg = conn.prepareStatement(sq_rg);
                    try{
                        pst_rg.setInt(1, Integer.parseInt(searchFormat_rg));
                    } catch (Exception e) {
                        System.out.println("Input is not date.");
                        pst_rg.setString(1, searchFormat_rg);
                    }
                    pst_rg.setString(2, searchFormat_rg);
                    //look for user w that name; get id
                    String squ_rg = "SELECT * FROM User WHERE UserFName LIKE ? OR UserLName LIKE ?";
                    String searchFormatu_rg = "%" + searchInput_rg + "%";
                    PreparedStatement pstu_rg = conn.prepareStatement(squ_rg);
                    pstu_rg.setString(1, searchFormatu_rg);
                    pstu_rg.setString(2, searchFormatu_rg);
                    ResultSet rsu = pstu_rg.executeQuery();
                    if(rsu.next()){
                        int uid = rsu.getInt("User");
                        pst_rg.setInt(3, uid); //id to name
                    }
                    try {
                        pst_rg.setInt(3,  Integer.parseInt(searchFormat_rg)); //if no user
                    } catch (Exception e) {
                        System.out.println("Input is not integer.");
                        pst_rg.setString(3, searchFormat_rg);
                    }
                }
                else{
                    sq_rg = "SELECT * FROM TicketPurchase";
                    pst_rg = conn.prepareStatement(sq_rg);
                }*/
                sq_rg = "SELECT UserID, UserEmail, UserFName, UserLName, UserContact, UserType FROM User";
                pst_rg = conn.prepareStatement(sq_rg);
                
                //insert query
                ArrayList <String[]> records_rg = new ArrayList();
                ResultSet rs = pst_rg.executeQuery();
                
                while (rs.next()){
                    records_rg.add(new String[]{
                        rs.getString("UserID"),
                        rs.getString("UserEmail"),
                        rs.getString("UserFName"),
                        rs.getString("UserLName"),
                        rs.getString("UserContact"),
                        rs.getString("UserType"),
                    });
                    
                if (records_rg.size() > 0) 
                    System.out.println("array has items");                
                }
                
                request.setAttribute("records", records_rg);
                request.getRequestDispatcher("AdminUsers.jsp").forward(request, response);
                
            } catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e){
            System.out.println("ewan");
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