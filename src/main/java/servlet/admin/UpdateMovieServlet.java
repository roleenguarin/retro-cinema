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
public class UpdateMovieServlet extends HttpServlet {

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
                
                //parameters
                String table = "Movie";
                String idvar = "MovieID";
                String recordID = request.getParameter("recordID");
                System.out.println(recordID);
                String title = request.getParameter("title");
                String duration = request.getParameter("duration"); //INT Integer.parseInt(
                String releasein = request.getParameter("release");
                String description = request.getParameter("description");
                String genres = request.getParameter("genres");
                String director = request.getParameter("director");
                String ageRating = request.getParameter("ageRating");
                String starRating = request.getParameter("starRating");//DOUBLE Double.parseDouble(
                String status = request.getParameter("status");
                
                //update record
                Part poster;
                try {
                    poster = request.getPart("poster");
                    if (!poster.equals("")){
                        // image
                        String posterName = Paths.get(poster.getSubmittedFileName()).getFileName().toString();
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
                        String updateString = "UPDATE MOVIE SET MoviePoster = ? WHERE MovieID = ?";
                        pst_rg = conn.prepareStatement(updateString);

                        pst_rg.setString(1, posterName);
                        pst_rg.setInt(2, Integer.parseInt(recordID));
                        pst_rg.executeUpdate();
      
                        System.out.println("Poster Updated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"); 
                    }
                } catch (Exception e) {
                    System.out.println("No poster input.");
                }
                if (!title.equals("")){
                     DBFunctions.update(table, "MovieTitle", title, idvar, recordID);
                }
                if (!duration.equals("")){
                    String updateString = "UPDATE MOVIE SET MovieDuration = ? WHERE MovieID = ?";
                    pst_rg = conn.prepareStatement(updateString);

                    pst_rg.setDouble(1, Double.parseDouble(duration));
                    pst_rg.setInt(2, Integer.parseInt(recordID));
                    pst_rg.executeUpdate();
                    System.out.println("duration Updated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"); 
                }
                if (!releasein.equals("")){
                    String updateString = "UPDATE MOVIE SET MovieDuration = ? WHERE MovieID = ?";
                    pst_rg = conn.prepareStatement(updateString);

                    java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(releasein);
                    Date release = new Date(date.getTime());
                    pst_rg.setDate(1, release);
                    pst_rg.setInt(2, Integer.parseInt(recordID));
                    pst_rg.executeUpdate();
                    System.out.println("release Updated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"); 
                }
                if (!description.equals("")){
                    DBFunctions.update(table, "MovieDesc", description, idvar, recordID);
                }
                if (!genres.equals("")){
                    DBFunctions.update(table, "Genre", genres, idvar, recordID);
                }
                if (!director.equals("")){
                    DBFunctions.update(table, "Director", director, idvar, recordID);
                }
                if (!ageRating.equals("")){
                    DBFunctions.update(table, "AgeRating", ageRating, idvar, recordID); 
                }
                if (!request.getParameter("starRating").equals("")){
                    String updateString = "UPDATE MOVIE SET StarRating = ? WHERE MovieID = ?";
                    pst_rg = conn.prepareStatement(updateString);

                    pst_rg.setDouble(1, Double.parseDouble(starRating));
                    pst_rg.setInt(2, Integer.parseInt(recordID));
                    pst_rg.executeUpdate();
                    System.out.println("starRating Updated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"); 
                }
                if (!status.equals("")){
                    DBFunctions.update(table, "MovieStatus", status, idvar, recordID);
                }
                
                //back to main page
                request.getRequestDispatcher("AdminServlet").forward(request, response);
                
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