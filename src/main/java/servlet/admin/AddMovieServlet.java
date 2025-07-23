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
@MultipartConfig
public class AddMovieServlet extends HttpServlet {

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
                String posterName = null;
                try {
                    Part poster = request.getPart("poster");
                    // image
                    posterName = Paths.get(poster.getSubmittedFileName()).getFileName().toString();
                    String posterPath = "Movie Posters" + File.separator  + posterName;
                    String uploadPath = getServletContext().getRealPath("/Movie Posters");
                    new File(uploadPath).mkdirs();
                    System.out.println("Upload path: " + uploadPath);
                    System.out.println("postername: " + posterName);
                    poster.write(posterName); //uploadPath + File.separator + posterName
                    Files.move(
                        Paths.get("C:/Users/Roleen/GlassFish_Server/glassfish/domains/domain1/generated/jsp/GUARIN_MovieBooking" + File.separator + posterName),
                        Paths.get(uploadPath + File.separator + posterName),
                        StandardCopyOption.REPLACE_EXISTING
                    );
                } catch (Exception e) {
                    System.out.println("No poster input.");
                    //redirect to add
                    request.getRequestDispatcher("AdminServlet").forward(request, response);
                }
                parameters.add(posterName);
                parameters.add(request.getParameter("title"));
                parameters.add(request.getParameter("duration"));//DOUBKE Double.parseDouble(
                parameters.add(request.getParameter("release"));
                parameters.add(request.getParameter("description"));
                parameters.add(request.getParameter("genres"));
                parameters.add(request.getParameter("director"));
                parameters.add(request.getParameter("ageRating"));
                parameters.add(request.getParameter("starRating"));//DOUBLE Double.parseDouble(
                parameters.add(request.getParameter("status"));
                
                //check if all rnt null
                boolean noneNull = false;
                for (String a : parameters) {
                    if (a==null)
                        noneNull = true;
                }
                
                //add input to db , if all rnt null
                if (noneNull){ //posterName!=null && title!=null && duration!=null && releasein!=null && description!=null && genres!=null && director!=null && ageRating!=null && starRating!=null && status!=null
                    DBFunctions.addMovie(parameters);
                }
                
                request.getRequestDispatcher("AdminServlet").forward(request, response);
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