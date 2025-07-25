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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.DBFunctions;

/**
 *
 * @author Roleen
 */
public class AddEventsServlet extends HttpServlet {

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
                String cinema = request.getParameter("cinema");
                String day = request.getParameter("day");
                String time = request.getParameter("time");
                String payment = request.getParameter("payment");
                String user = request.getParameter("user");

                //add input to db , if all rnt null
                if (cinema != null && day != null && time != null && payment != null && user != null) {
                    try {
                        //check if sched is available (false-exists;true-output is null)
                        String schedid = DBFunctions.getSched(cinema, day, time);
                        if (schedid==null){
                            //create the sched
                            DBFunctions.addSched(cinema,day,time,"Event");
                            //create the event
                            DBFunctions.addEvent(schedid, payment, user);
                        }
                        else
                            request.getRequestDispatcher("AdminEventsServlet").forward(request, response);
                    } catch (Exception e) {
                        System.out.println("Error adding event.");
                    }
                }

                //fwd to page servlet
                request.getRequestDispatcher("AdminEventsServlet").forward(request, response);
            } catch (Exception e){ e.printStackTrace(); }
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
