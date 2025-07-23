<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title> <%if (session.getAttribute("usertype").equals("Employee")){%>Employee<%}%><%else{%>Admin<%}%> View</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="AdminEmployee.css">
        <link rel="stylesheet" href="general.css">
    </head>
  
    <body>
        <%
            request.setAttribute("path", session.getAttribute("path"));
        %>
        <div class="row d-flex justify-content-center align-items-start">
            <div id="left" class="col vh-100" width="308px" height="auto">
                <div id="leftcontent">
                    <div id="head">
                        <image id="Logo" src="Movie Posters<%= File.separator%>Cinema.png" height="120" alt="cinema logo"/>
                    </div>
                    <div id="navbar">
                        <a href="AdminServlet"><div id="movies">
                            <h2>Movies</h2>
                        </div></a>
                        <a href="AdminTicketServlet"><div id="tickets">
                            <h2>Tickets</h2> <!--seat(id, movie showing(movie, sched(cinema, date, time))) + user-->
                        </div></a>
                        <a href="AdminEventsServlet"><div id="events">
                            <h2>Events</h2>
                        </div></a>
                        <a href="AdminUsersServlet"><div id="users" <%if (session.getAttribute("usertype").equals("Employee")){%>hidden<%}%>>
                            <h2>Users</h2>
                        </div></a>
                        <a href="AdminActionsServlet"><div id="actions" <%if (session.getAttribute("usertype").equals("Employee")){%>hidden<%}%>>
                            <h2>Actions</h2>
                        </div></a>
                        <a href="AdminMovieShowsServlet"><div id="movieshowing">
                            <h2>Movie Showing</h2> <!--movie, sched(cinema, date, time)-->
                        </div></a>
                    </div>
                </div>
            </div>

            <div id="center" class="d-flex col justify-content-center align-items-end">
                <div>
                    <div id="head" class="d-flex flex-fill justify-content-center align-items-end">
                        <!--UN-->
                        <div id="UN" class="flex-column flex-fill" style="position:relative; display:inline-block;">
                            <form method="get" action="LogOutServlet">
                                <input hidden name="userid" value="<%=session.getAttribute("userid")%>">
                                <input id="logoutbtn" type="submit" name="logout" value="" class="overlay-btn">
                            </form>
                            <h3 id="AdminUN"><%=session.getAttribute("username")%></h3>
                            <h4 id="Role"><%=session.getAttribute("usertype")%></h4>
                        </div>
                        <div class="col-4 d-flex justify-content-end align-items-center flex-fill">
                            <h1>Action Log</h1>
                        </div>
                        <!--search-->
                        <div id="search" class="col d-flex justify-content-end flex-fill">
                            <form method="get" action="AdminActionsServlet">
                                <div id="search" class="d-flex row-flex align-items-end">
                                    <div class="col d-flex justify-content-end">
                                        <input id="searchbar" type="text" name="search" class="form-control d-inline-block w-auto" placeholder="Search...">
                                        <input id="searchbtn" type="submit" name="sub" value="Search" class="btn btn-success">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <!--table-->
                    <%
                        ArrayList <String[]> records_rg = (ArrayList <String[]>) request.getAttribute("records");
                    %>
                    <table id="adminMovieShoes">
                        <tr id="TableHeader">
                            <th>Showing ID</th>
                            <th>Movie</th>
                            <th>Sched (Cinema, Day, time)</th>
                            <th></th>
                            <th></th>
                        </tr>
                    <%
                        int rowCount = 1;
                        for (String[] field_rg : records_rg){
                    %>
                    <tr>
                        <td><%= field_rg[0] %></td>
                        <td><%= field_rg[1] %></td>
                        <td><!--<//%= //field_rg[2] %>-->
                            <table></table>
                        </td>
                        <form method="POST" action="UpdateTicket.jsp">
                            <td><input id="updatetbl" <%if (session.getAttribute("usertype").equals("Employee")){%>hidden<%}%> type="submit" name="update" value="Update" class="btn btn-success"></td>
                            <input type="hidden" name="recordID" value="<%= (field_rg[0])%>">
                            <input type="hidden" name="path" value="<%= session.getAttribute("path")%>">
                        </form>
                        <form method="POST" action="DeleteTicketServlet">
                            <td><input id="deletetbl" <%if (session.getAttribute("usertype").equals("Employee")){%>hidden<%}%> type="submit" name="delete" value="Delete" class="btn btn-success"></td>
                            <input type="hidden" name="recordID" value="<%= (field_rg[0])%>">
                            <input type="hidden" name="path" value="<%= session.getAttribute("path")%>">
                        </form>
                    </tr>
                    <%
                        }
                        rowCount++;
                    %>
                    </table>
                </div>
            </div>
                    
            <div id="right" class="col vh-100">
                <form method="POST" action="AddTicket.jsp">
                    <input id="addtbl" <%if (session.getAttribute("usertype").equals("Employee")){%>hidden<%}%> type="submit" name="add" value="Add" class="btn btn-success">
                    <input type="hidden" name="path" value="<%= session.getAttribute("path")%>">
                </form>
            </div>
        </div>
                
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    </body>
</html>