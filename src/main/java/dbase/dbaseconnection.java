package dbase;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbaseconnection {
    //database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3308/moviebooking_rg?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "Foman2607csb."; //moviebooking
    
    public static Connection getConnection(){
        Connection conn = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            
            System.out.println("Connected Successfully");
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        
        return conn;
    }
}