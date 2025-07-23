package servlet.admin;

import dbase.dbaseconnection;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import servlet.DBFunctions;

/**
 *
 * @author Roleen
 */
public class AddTicketServlet extends HttpServlet {

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
                //parameter variables
                String seatid = request.getParameter("seatid");
                String movie = request.getParameter("movie"); //will get movieid
                String cinema = request.getParameter("cinema"); ////dropdown
                String date = request.getParameter("date"); //will convert to Date; not just year
                String time = request.getParameter("time"); ////dropdown
                String payment = request.getParameter("payment"); ////dropdown
                String user = request.getParameter("user"); //will get userid; un na dapat yung input
                
                //add input to db , if all rnt null
                if (seatid!=null && movie!=null && cinema!=null && date!=null && time!=null && payment!=null && user!=null){
                    String movieid = DBFunctions.getMovie(movie);
                    if (movieid!=null){// get movieid; if null, back to main
                        System.out.println("Movie ["+movieid+". "+movie+"] exists.");
                    } else { //if null
                        System.out.println("Movie ["+movie+"] doesn't exist.");
                        request.getRequestDispatcher("AdminTicketServlet").forward(request, response);
                    }
                    
                    //initialize sched
                    String schedid = null;
                    try {
                        //check: sched == null, add sched; else, go to main page
                        if (DBFunctions.getSched(cinema, date, time)==null){
                            DBFunctions.addSched(cinema, date, time, "movie");
                            schedid = DBFunctions.getSched(cinema, date, time);
                        }
                        else {
                            request.getRequestDispatcher("AdminTicketServlet").forward(request, response);
                        }
                    } catch (Exception e) { System.out.println("Sched wasn't created fsr."); }
                    
                    //initialize showing
                    String showingid = null; 
                    try {
                        DBFunctions.addShowing(movieid, schedid);
                        //get showingid
                        showingid = DBFunctions.getShowing(movieid, schedid);
                    } catch (Exception e) { System.out.println("Show wasn't created fsr."); }
                    
                    //initialize seat
                    ArrayList<String> ticketseat = new ArrayList<>();
                    try {
                        DBFunctions.addSeat(seatid, showingid);
                        //get seatid
                        ticketseat = DBFunctions.getSeat(seatid, showingid);
                    } catch (Exception e){ System.out.println("Show wasn't created fsr"); }
                    
                    //add ticket purchase
                    try { 
                        DBFunctions.addTicket(ticketseat, payment, user);
                    } catch (Exception e){
                        System.out.println("May error sa pag-add.");
                    }
                }
                
                request.getRequestDispatcher("AdminTicketServlet").forward(request, response);
                
            } catch (Exception e){
                e.printStackTrace();
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