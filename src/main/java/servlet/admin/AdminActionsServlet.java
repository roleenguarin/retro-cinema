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
public class AdminActionsServlet extends HttpServlet {

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
                
                String searchInput_rg = request.getParameter("search");
                
                if(searchInput_rg != null  && !searchInput_rg.trim().isEmpty()){
                    sq_rg = "SELECT * FROM ActionLogs WHERE ActioDone LIKE ?";
                    String searchFormat_rg = "%" + searchInput_rg + "%";
                    pst_rg = conn.prepareStatement(sq_rg);
                    pst_rg.setString(1, searchFormat_rg);
                }
                else{
                    sq_rg = "SELECT * FROM ActionLogs";
                    pst_rg = conn.prepareStatement(sq_rg);
                }
                
                //insert query
                ArrayList <String[]> records_rg = new ArrayList();
                ResultSet rs = pst_rg.executeQuery();
                
                while (rs.next()){
                    records_rg.add(new String[]{
                        rs.getString("LogID"),
                        rs.getString("UserID"),
                        rs.getString("ActionDone"),
                        rs.getString("WhenDone")
                    });
                    
                if (records_rg.size() > 0) 
                    System.out.println("array has items");                
                }
                
                request.setAttribute("records", records_rg);
                request.getRequestDispatcher("AdminActions.jsp").forward(request, response);
                
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