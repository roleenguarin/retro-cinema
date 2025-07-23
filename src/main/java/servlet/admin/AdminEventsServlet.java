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
public class AdminEventsServlet extends HttpServlet {

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
                
                sq_rg = "SELECT * FROM EventBooking";
                pst_rg = conn.prepareStatement(sq_rg);
                
                //insert query
                ArrayList <String[]> records_rg = new ArrayList();
                ResultSet rs = pst_rg.executeQuery();
                
                while (rs.next()){
                    String squ_rg = "SELECT UserFName,UserLName FROM User WHERE UserID LIKE ?";
                    String searchFormatu_rg = rs.getString("User");
                    PreparedStatement pstu_rg = conn.prepareStatement(squ_rg);
                    pstu_rg.setString(1, searchFormatu_rg);
                    ResultSet rsu = pstu_rg.executeQuery();
                    String sqs_rg = "SELECT Cinema,Day,Timeslot FROM Sched WHERE ScheduleID = ?";
                    String searchFormats_rg = rs.getString("BookingSchedule");
                    PreparedStatement psts_rg = conn.prepareStatement(sqs_rg);
                    psts_rg.setString(1, searchFormats_rg);
                    ResultSet rss = psts_rg.executeQuery();
                    if (rsu.next());
                    if (rss.next());
                    records_rg.add(new String[]{
                        rs.getString("BookingID"),
                        rs.getString("BookingSchedule"),
                        rss.getString("Cinema"),
                        rss.getString("Day"),
                        rss.getString("Timeslot"),
                        rs.getString("PaymentMethod"),
                        rs.getString("User"),
                        rsu.getString("UserFName"),
                        rsu.getString("UserLName")
                    });
                    
                if (records_rg.size() > 0) 
                    System.out.println("array has items");                
                }
                
                request.setAttribute("records", records_rg);
                request.getRequestDispatcher("AdminEvents.jsp").forward(request, response);
                
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