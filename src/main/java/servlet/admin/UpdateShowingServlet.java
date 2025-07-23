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
public class UpdateShowingServlet extends HttpServlet {

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
                String table = "MovieShowing";
                String idvar = "ShowingID";
                String recordID = request.getParameter("recordID"); //BookingID
                System.out.println(recordID);

                //input parameters
                String movie = request.getParameter("movie"); //dropdown of now showing?
                String cinema = request.getParameter("cinema"); //dropdown
                String date = request.getParameter("day");
                String time = request.getParameter("time");  //dropdown
                
                //update record
                if(!movie.equals("")){
                    movie = DBFunctions.getVar("MovieID", "Movie", "MovieTitle", movie);
                    String updateString = "UPDATE "+table+" SET ShowingMovie = "+Integer.parseInt(movie)+" WHERE "+idvar+" = "+recordID;
                    pst_rg = conn.prepareStatement(updateString);
                    pst_rg.executeUpdate();
                    System.out.println(table + ": ShowingMovie Updated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }
                if(!cinema.equals("")){
                    String sched = DBFunctions.getVar("BookingSchedule",table,recordID,idvar);
                    date = DBFunctions.getVar("Cinema","Day",sched,"ScheduleID");
                    time = DBFunctions.getVar("Cinema","Timeslot",sched,"ScheduleID");
                    if(DBFunctions.getSched(cinema, date, time)==null){
                        String updateString = "UPDATE Sched SET Cinema = ? WHERE ScheduleID = ?";
                        pst_rg = conn.prepareStatement(updateString);

                        pst_rg.setInt(1, Integer.parseInt(cinema));
                        pst_rg.setInt(2, Integer.parseInt(sched));
                        pst_rg.executeUpdate();
                        System.out.println(table + ": Cinema Updated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    }
                }
                if(!date.equals("")){
                    String sched = DBFunctions.getVar("BookingSchedule",table,recordID,idvar);
                    cinema = DBFunctions.getVar("Cinema","Cinema",sched,"ScheduleID");
                    time = DBFunctions.getVar("Cinema","Timeslot",sched,"ScheduleID");
                    if(DBFunctions.getSched(cinema, date, time)==null){
                        String updateString = "UPDATE Sched SET Day = ? WHERE ScheduleID = ?";
                        pst_rg = conn.prepareStatement(updateString);

                        pst_rg.setInt(1, Integer.parseInt(date));
                        pst_rg.setInt(2, Integer.parseInt(sched));
                        pst_rg.executeUpdate();
                        System.out.println(table + ": Date Updated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    }
                }
                if(!time.equals("")){
                    String sched = DBFunctions.getVar("BookingSchedule",table,recordID,idvar);
                    cinema = DBFunctions.getVar("Cinema","Cinema",sched,"ScheduleID");
                    date = DBFunctions.getVar("Cinema","Day",sched,"ScheduleID");
                    if(DBFunctions.getSched(cinema, date, time)==null)
                        DBFunctions.update("Timeslot","Sched",time,"ScheduleID",sched);
                }
                
                //back to main page
                request.getRequestDispatcher("AdminMovieShowsServlet").forward(request, response);
                
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
