<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="general.css">
        <link rel="stylesheet" href="HomeCSS.css">
    </head>
  
    <body  id="Home">
        <div class="row d-flex justify-content-center" id="header">
            <div class="d-flex flex-wrap col-2 justify-content-center align-items-center">
                <image id="headLogo" src="Movie Posters<%= File.separator%>Cinema.png" height="80" alt="cinema logo"/>
            </div>
            <div class="d-flex flex-wrap col-1 justify-content-center align-items-end">
                <h2 id="head"><a id="head" href="HomeServlet">Home</a></h2>
            </div>
            <div class="d-flex flex-wrap col-1 justify-content-center align-items-end">
                <h2 id="head"><a id="head" href="MoviesServlet">Movies</a></h2>
            </div>
            <div class="d-flex flex-wrap col-1 justify-content-center align-items-end">
                <h2 id="head"><a id="head" href="CinemasServlet">Cinemas</a></h2>
            </div>
            <div class="d-flex flex-wrap col-1 justify-content-center align-items-end">
                <h2 id="head"><a id="head" href="EventsServlet">Events</a></h2>
            </div>
            <!--<div class="d-flex flex-wrap col-1 justify-content-center align-items-end">
                <h2 id="head"><a href="MenuServlet?Menu=Home">Menu</h2>
            </div>-->
            <div id="UN" class="d-flex flex-wrap col-1 justify-content-center align-items-end" style="position:relative; display:inline-block;">
                <form method="get" action="LogOutServlet">
                    <input hidden name="userid" value="<%=session.getAttribute("userid")%>">
                    <input id="logoutbtn" type="submit" name="logout" value="" class="overlay-btn">
                </form>
                <h3 id="head"><%=session.getAttribute("username")%></h3>
            </div>
        </div>
        
            
        <div class="d-flex col justify-content-center" id="MainBG">
            <div class="d-flex row justify-content-center" id="MainSection"><!--class="container-->
                <div id="Home_NowShowing">
                    <%
                        ArrayList <String[]> records_rg = (ArrayList <String[]>) request.getAttribute("nowshowing_records");
                    %>
                    <image src="<%= session.getAttribute("path")%>NowShowingBanner.png" id="Banner" height="63">
                        <div class="row d-flex justify-content-start" id="MovieRow">
                        <%
                            int i = 0 ;
                            for (String[] field_rg : records_rg){
                                i++;
                        %>
                            <div class="d-flex flex-wrap col-1 justify-content-center align-items-center" id="Pagelet" style="position: relative;">
                                <img src="<%= session.getAttribute("path")%>homenspins.png" style="z-index: 4; position: absolute;">
                                <img id="MoviePoster" src="<%= session.getAttribute("path") + field_rg[10] %>" alt="<%=field_rg[10]%>"  width="auto" height="200" style="position: relative;">
                                <h3 id="MovieTitle" style="position: relative;"><%= field_rg[1] %></h3> 
                                <div class="row-flex d-flex flex-wrap justify-content-center align-items-start" id="MovieGenres" style="position: relative;">
                                    <%
                                        String [] genres = field_rg[5].split(", ") ;
                                        for(int a=0;a<genres.length;a++){
                                    %>
                                    <div id="genre" class="d-flex col-1 justify-content-start align-items-center" style="position: relative;">
                                        <p style="position: relative;"><%= genres[a] %></p>
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>
                                <form method="POST" action='BuyTicketServlet' style="position: relative;">
                                    <input type="submit" class="btn btn-primary" id="Home_BuyTicket" value="Buy Ticket">
                                </form>
                            </div>
                        <%
                            if (i==5)
                                break;
                            }
                        %>
                        </div>
                </div>
                <div id="Home_ComingSoon">
                    <%
                        records_rg = (ArrayList <String[]>) request.getAttribute("comingsoon_records");
                    %>
                    <image src="<%= session.getAttribute("path")%>ComingSoonBanner.png" id="Banner" height="63">
                        <div class="row d-flex justify-content-start" id="MovieRow">
                        <%
                            i = 0 ;
                            for (String[] field_rg : records_rg){
                                i++;
                        %>
                            <div class="d-flex flex-wrap col-1 justify-content-center align-items-center" id="Pagelet" style="position: relative;">
                                <img src="<%= session.getAttribute("path")%>pins.png" style="z-index: 4; position: absolute;">
                                <img id="MoviePoster" src="<%= session.getAttribute("path") + field_rg[10] %>" alt="<%=field_rg[10]%>" width="135" height="200" style="position: relative;">
                                <h3 id="MovieTitle" style="position: relative;"><%= field_rg[1] %></h3>
                                
                                <div class="row-flex d-flex flex-wrap justify-content-center align-items-center" id="MovieGenres" style="position: relative;">
                                    <%
                                        String [] genres = field_rg[5].split(", ") ;
                                        for(int a=0;a<genres.length;a++){
                                    %>
                                    <div id="genre" class="d-flex col-1 justify-content-start align-items-center" style="position: relative;">
                                        <p style="position: relative;"><%= genres[a] %></p>
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>
                            </div>
                        <%
                            if (i==5)
                                break;
                            }
                        %>
                        </div>
                </div>
            </div>
        </div>
        

        <div class="row d-flex justify-content-center" id="footer" style="background-image: url(<%= session.getAttribute("path")%>FooterBG.png);">
            <div class="d-flex flex-column flex-wrap col-1 justify-content-center align-items-center">
                <p id="foot"><a id="foot" href="HomeServlet">Home</a></p>
                <p id="foot"><a id="foot" href="MoviesServlet">Movies</a></p>
                <p></p>
            </div>
            <div class="d-flex flex-column flex-wrap col-1 justify-content-center align-items-center">
                <p id="foot"><a id="foot" href="CinemasServlet">Cinemas</a></p>
                <p id="foot"><a id="foot" href="EventsServlet"></a>Events</p>
                <p></p>
            </div>
            <div class="d-flex flex-column flex-wrap col-1 justify-content-center align-items-center">
                <p id="foot">Contact Us</p>
                <p id="foot">mobile number</p>
                <p id="foot">landline number</p>
            </div>
            <div class="d-flex flex-wrap col-1 justify-content-center align-items-center">
                <image src="<%= session.getAttribute("path")%>Cinema.png" width="auto" height="90" alt="cinema logo"/>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>