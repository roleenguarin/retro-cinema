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
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.DBFunctions;

/**
 *
 * @author Roleen
 */
public class AddShowingServlet extends HttpServlet {

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
                ArrayList<String> parameters = new ArrayList<String>();
                String movie = request.getParameter("movie");
                String cinema  = request.getParameter("cinema");
                String day = request.getParameter("day");
                String time = request.getParameter("time");
                parameters.add(movie);
                parameters.add(cinema);
                parameters.add(day);
                parameters.add(time);

                //check if all rnt null
                boolean noneNull = false;
                for (String a : parameters) {
                    if (a == null) {
                        noneNull = true;
                    }
                }

                //add input to db , if all rnt null
                if (noneNull) {                    
                    try{
                        //check if sched exists/true (=unavailable)
                        if (DBFunctions.getSched(cinema, day, time) == null) { //doesnt exist
                            //create sched
                            DBFunctions.addSched(cinema, day, time, "Movie");
                            //create showing
                            DBFunctions.addShowing(DBFunctions.getMovie(movie), DBFunctions.getSched(cinema, day, time));
                        } else {
                            //back to showing page
                            request.getRequestDispatcher("AdminShowingServlet").forward(request, response);
                        }
                    }catch (Exception e) { System.out.println("Error adding showing."); }
                }

                request.getRequestDispatcher("AdminShowingServlet").forward(request, response);
            } catch (Exception e) { e.printStackTrace(); }
            
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
