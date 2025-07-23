package servlet.customer;

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
public class MoviesComingSoonServlet extends HttpServlet {

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
                
                 //SEARCH
                String searchInput_rg = request.getParameter("search");
                if(searchInput_rg != null  && !searchInput_rg.trim().isEmpty()){
                    sq_rg = "SELECT * FROM Movie WHERE MovieStatus LIKE 'Coming Soon' AND (MovieTitle LIKE ? OR Genre LIKE ? OR MovieDesc LIKE ?)";
                    String searchFormat_rg = "%" + searchInput_rg + "%";
                    System.out.println(searchFormat_rg);
                    pst_rg = conn.prepareStatement(sq_rg);
                    pst_rg.setString(1, searchFormat_rg);
                    pst_rg.setString(2, searchFormat_rg);
                    pst_rg.setString(3, searchFormat_rg);
                }
                else{
                    sq_rg = "SELECT * FROM Movie WHERE MovieStatus LIKE 'Coming Soon'";
                    pst_rg = conn.prepareStatement(sq_rg);
                }
                
                //GETTING RESUTLS
                ArrayList <String[]> comingsoon_records_rg = new ArrayList();
                String path = "Movie Posters" + File.separator;
                request.setAttribute("path", path);
                ResultSet rs = pst_rg.executeQuery();
                
                while (rs.next()){
                    comingsoon_records_rg.add(new String[]{
                        rs.getString("MovieID"),
                        rs.getString("MovieTitle"),
                        rs.getString("MovieDuration"),
                        rs.getString("MovieReleaseDate"),
                        rs.getString("MovieDesc"),
                        rs.getString("Genre"),
                        rs.getString("Director"),
                        rs.getString("AgeRating"),
                        rs.getString("StarRating"),
                        rs.getString("MovieStatus"),
                        rs.getString("MoviePoster")
                    });
                    
                if (comingsoon_records_rg.size() > 0) 
                    System.out.println("array has items");                
                }
                request.setAttribute("comingsoon_records", comingsoon_records_rg);
                request.getRequestDispatcher("MoviesComingSoon.jsp").forward(request, response);
                
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