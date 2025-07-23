/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dbase.dbaseconnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Roleen
 */
public class DBFunctions {
    // query variables
    static Connection conn = dbaseconnection.getConnection();
    static PreparedStatement pst_rg = null;
    static String addString = null;
    static String findString = null;
    static ResultSet rs_rg = null;
    
    //////////////////////////////////////////////
    //INSERTS/////////////////////////////////////
    //////////////////////////////////////////////
    public static void addMovie(ArrayList<String> parameters) throws SQLException { //String posterName, String title, String duration, String releasein, String description, String genres, String director, String ageRating, String starRating, String status
        addString = "INSERT INTO Movie (MoviePoster, MovieTitle, MovieDuration, MovieReleaseDate, MovieDesc, Genre, Director, AgeRating, StarRating, MovieStatus) VALUES (?,?,?,?,?,?,?,?,?,?)";//
        
        try {
            pst_rg = conn.prepareStatement(addString);
            pst_rg.setString(1, parameters.get(0));
            pst_rg.setString(2, parameters.get(1));
            pst_rg.setDouble(3, Double.parseDouble(parameters.get(2)));
            pst_rg.setInt(4, Integer.parseInt(parameters.get(3)));
            pst_rg.setString(5, parameters.get(4));
            pst_rg.setString(6, parameters.get(5));
            pst_rg.setString(7, parameters.get(6));
            pst_rg.setString(8, parameters.get(7));
            pst_rg.setDouble(9, Double.parseDouble(parameters.get(8)));
            pst_rg.setString(10, parameters.get(9));
            pst_rg.executeUpdate();
        } catch (Exception e) {
            System.out.println("May error sa pag-add.");
        }
    }
    public static void addSched(String cinema, String day, String time, String schedtype) throws SQLException, ParseException {
        addString = "INSERT INTO Sched (Cinema, Day, Timeslot, SchedType) VALUES (?,?,?,?)";
        pst_rg = conn.prepareStatement(addString);
        pst_rg.setInt(1, Integer.parseInt(cinema));
        java.util.Date convDate = new SimpleDateFormat("yyyy-MM-dd").parse(day);
        Date dbDate = new Date(convDate.getTime());
        pst_rg.setDate(2, dbDate);
        pst_rg.setString(3, time);
        pst_rg.setString(4, schedtype);
        pst_rg.executeUpdate();
    }
    public static void addTicket(ArrayList<String> ticketseat, String payment, String user) throws SQLException {
        addString = "INSERT INTO TicketPurchase (TicketSeatID, TicketSeatShow, PaymentMethod, User) VALUES (?,?,?,?)";
        pst_rg = conn.prepareStatement(addString);
        pst_rg.setString(1, ticketseat.get(0));
        pst_rg.setInt(2, Integer.parseInt(ticketseat.get(1)));
        pst_rg.setString(3, payment);
        pst_rg.setInt(4, Integer.parseInt(user));
        pst_rg.executeUpdate();
    }
    public static void addEvent(String schedid, String payment, String user) throws SQLException {
        addString = "INSERT INTO EventBooking (BookingSchedule, PaymentMethod, User) VALUES (?,?,?)";
        pst_rg = conn.prepareStatement(addString);
        pst_rg.setString(1, schedid);
        pst_rg.setString(2, payment);
        pst_rg.setInt(3, Integer.parseInt(user));
        pst_rg.executeUpdate();
    }
    public static void addUser(ArrayList<String> parameters) throws SQLException {
        addString = "INSERT INTO User (UserEmail, UserFName, UserLName, UserContact, UserPassword, UserType) VALUES (?,?,?,?,?,?)";
        pst_rg = conn.prepareStatement(addString);
        pst_rg.setString(1, parameters.get(0));
        pst_rg.setString(2, parameters.get(1));
        pst_rg.setString(3, parameters.get(2));
        pst_rg.setInt(4, Integer.parseInt(parameters.get(3)));
        pst_rg.setString(5, parameters.get(4));
        pst_rg.setString(6, parameters.get(5));
        pst_rg.executeUpdate();
    }
    public static void addShowing(String movieid, String schedid) throws SQLException {
        addString = "INSERT INTO MovieShowing (ShowingMovie, ShowingSched) VALUES (?,?)";
        pst_rg = conn.prepareStatement(addString);
        pst_rg.setInt(1, Integer.parseInt(movieid));
        pst_rg.setInt(2, Integer.parseInt(schedid));
        pst_rg.executeUpdate();
    }
    public static void addSeat(String seatid, String showingid) throws SQLException {
        addString = "INSERT INTO Seat (SeatID, SeatShow) VALUES (?,?)";
        pst_rg = conn.prepareStatement(addString);
        pst_rg.setString(1, seatid);
        pst_rg.setInt(2, Integer.parseInt(showingid));
        pst_rg.executeUpdate();
    }
    //////////////////////////////////////////////
    //GET VAR/////////////////////////////////////
    public static String getVar(String variable, String table, String idvar, String recordid) throws SQLException {
        findString = "SELECT "+variable+" FROM "+table+" WHERE "+idvar+" = "+recordid;
        String value = null;
        pst_rg = conn.prepareStatement(findString);
        ResultSet rs = pst_rg.executeQuery();
        while (rs.next()) {
            value = rs.getString(variable);}
        return value;
    }
    //////////////////////////////////////////////
    //GETS////////////////////////////////////////
    //////////////////////////////////////////////
    public static String getMovie(String movie) throws SQLException {
        String movieid = null;
        findString = "SELECT MovieID FROM Movie WHERE MovieTitle LIKE ?";
        pst_rg = conn.prepareStatement(findString);
        pst_rg.setString(1, movie);
        rs_rg = pst_rg.executeQuery();
        if (rs_rg.next()) {
            movieid = "" + rs_rg.getInt("MovieID");
            System.out.println("Movie [" + movieid + ". " + movie + "] exists.");
        }
        
        return movieid;
    }
    public static ArrayList<String> getCharacters(String movieid) throws SQLException {
        ArrayList<String> characs  = new ArrayList<String>();
        findString = "SELECT * FROM MovieCharacter WHERE MovieID LIKE ?";
        pst_rg = conn.prepareStatement(findString);
        pst_rg.setString(1, movieid);
        rs_rg = pst_rg.executeQuery();
        while (rs_rg.next()) {
            characs.add(rs_rg.getString("CharacterName")+" as "+rs_rg.getString("ActorName"));
        }
        
        return characs;
    }
    public static String getSched(String cinema, String day, String time) throws SQLException, ParseException {
        String schedid = null;
        findString = "SELECT ScheduleID FROM Sched WHERE Cinema LIKE ? AND Day LIKE ? AND Timeslot LIKE ?";
        pst_rg = conn.prepareStatement(findString);
        pst_rg.setInt(1, Integer.parseInt(cinema));
        java.util.Date convDate = new SimpleDateFormat("yyyy-MM-dd").parse(day);
        Date dbDate = new Date(convDate.getTime());
        pst_rg.setDate(2, dbDate);
        pst_rg.setString(3, time);
        rs_rg = pst_rg.executeQuery();
        if (rs_rg.next()) {
            schedid = "" + rs_rg.getInt("ScheduleID");
            System.out.println("Sched [" + schedid + ". " + dbDate + "] exists.");
        }
        
        return schedid;
    }
    public static String getTicket(ArrayList<String> ticketseat, String payment, String user) throws SQLException {
        String ticketid = null;
        findString = "SELECT PurchaseID FROM TicketPurchase WHERE TicketSeatID LIKE ? AND TicketSeatShow LIKE ? AND PaymentMethod LIKE ? AND User LIKE ?";
        pst_rg = conn.prepareStatement(findString);
        pst_rg.setString(1, ticketseat.get(0));
        pst_rg.setInt(2, Integer.parseInt(ticketseat.get(1)));
        pst_rg.setString(3, payment);
        pst_rg.setInt(3, Integer.parseInt(user));
        rs_rg = pst_rg.executeQuery();
        if (rs_rg.next()) {
            ticketid = "" + rs_rg.getInt("ScheduleID");
            System.out.println("Ticket [" + ticketid + ". " + ticketseat.get(0) + "] exists.");
        }
        
        return ticketid;
    }
    public static String getEvent() throws SQLException {
        String eventid = null;
        
        
        return eventid;
    }
    public static String getUser() throws SQLException {
        String userid = null;
        
        
        return userid;
    }
    public static String getShowing(String movieid, String schedid) throws SQLException {
        String showingid = null;
        findString = "SELECT ShowingID FROM MovieShowing WHERE ShowingMovie LIKE ? AND ShowingSched LIKE ?";
        pst_rg = conn.prepareStatement(findString);
        pst_rg.setInt(1, Integer.parseInt(movieid));
        pst_rg.setInt(2, Integer.parseInt(schedid));
        rs_rg = pst_rg.executeQuery();
        if (rs_rg.next()) {
            showingid = "" + rs_rg.getInt("ShowingID");
            System.out.println("Show [" + showingid + ". " + movieid + "," + schedid + "] created.");
        }
        
        return showingid;
    }
    public static ArrayList<String> getSeat(String seatid, String showingid) throws SQLException {
        ArrayList<String> ticketseat = new ArrayList<>();
        findString = "SELECT SeatID, SeatShow FROM Seat WHERE SeatID LIKE ? AND SeatShow LIKE ?";
        pst_rg = conn.prepareStatement(findString);
        pst_rg.setString(1, seatid);
        pst_rg.setInt(2, Integer.parseInt(showingid));
        rs_rg = pst_rg.executeQuery();
        if (rs_rg.next()) {
            ticketseat.add(rs_rg.getString("SeatID"));
            ticketseat.add(""+rs_rg.getInt("SeatShow")); //convert to int
            System.out.println("Seat [" + ticketseat + "] found.");
        }
        
        return ticketseat;
    }
    
    //UPDATES
    public static void update(String table, String variable, String value, String idvar, String record) throws SQLException{
        String updateString = "UPDATE "+table+" SET "+variable+" = ? WHERE "+idvar+" = ?";
        pst_rg = conn.prepareStatement(updateString);

        pst_rg.setString(1, value);
        pst_rg.setInt(2, Integer.parseInt(record));
        pst_rg.executeUpdate();
        System.out.println(table+": "+variable+" Updated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    
    //DELETES
    public static void delete(String table, String idvar, String record) throws SQLException{
        String deleteString = "DELETE FROM "+table+" WHERE "+idvar+" = ?";
        pst_rg = conn.prepareStatement(deleteString);
        
        pst_rg.setString(1, record);
        pst_rg.executeUpdate();
        System.out.println(table+": "+idvar+"("+record+") Updated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    
}
