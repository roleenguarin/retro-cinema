<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Admin View</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="AdminEmployee.css">
        <link rel="stylesheet" href="general.css">
    </head>
  
    <body>
        <div class="row d-flex justify-content-center align-items-start">
            <div id="left" class="col vh-100" width="308px" height="auto">
                <div id="leftcontent" class="container">
                    <div id="head">
                        <image id="Logo" src="Movie Posters<%= File.separator%>Cinema.png" height="120" alt="cinema logo"/>
                    </div>
                    <div id="navbar">
                        <div id="movies">
                            <h2>Movies</h2>
                        </div>
                        <div id="tickets">
                            <h2>Tickets</h2>
                        </div>
                        <div id="cinemas">
                            <h2>Cinemas</h2>
                        </div>
                        <div id="events">
                            <h2>Events</h2>
                        </div>
                        <div id="users">
                            <h2>Users</h2>
                        </div>
                        <div id="actions">
                            <h2>Actions</h2>
                        </div>
                        <div id="approval">
                            <h2>Approval</h2>
                        </div>
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
                            <h1>Movie Showings</h1>
                        </div>
                    </div>

                    <!--table-->
                    <table id="updateMovie"> <!-- class="table table-primary table-hover table-stripped"-->
                       <tr id="TableHeader">
                            <th></th>
                            <th>Showing Movie</th>
                            <th>Cinema</th>
                            <th>Day</th>
                            <th>Time</th>
                            <th></th>
                        </tr>
                        <tr>
                            <form method="POST" action="UpdateShowingServlet">
                                <td></td>
                                <td><input name="movie" type="text" class="form-control">  </td>
                                <td><input name="cinema" type="text" class="form-control">  </td>
                                <td><input name="day" type="text" class="form-control">  </td>
                                <td><input name="time" type="text" class="form-control">  </td> </td>
                                <td><input id="updatetbl" type="submit" name="updaterecord" value="Update" class="btn btn-success"></td>
                                <input id="updatetbl" type="hidden" name="recordID" value="<%=request.getParameter("recordID")%>" class="btn btn-success">
                            </form>
                        </tr>
                    </table>
                </div>
            </div>
            <div id="right" class="col vh-100"></div>
        </div>
                
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    </body>
</html>