<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Employee View</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="MovieBookingCSS.css">
    </head>
  
    <body>
        <div class="container">
            <div class="container my-4">
                <form method="get" action="EmployeeServlet">
                    <div class="row mb-3">
                        <div class="col text-end">
                            <input type="text" name="search" class="form-control d-inline-block w-auto" placeholder="Search...">
                            <input type="submit" name="sub" value="Search" class="btn btn-success">
                        </div>
                    </div>
                </form>
            </div>
            <%
                ArrayList <String[]> records_rg = (ArrayList <String[]>) request.getAttribute("records");
            %>
            <table class="table table-primary table-hover table-stripped">
                <tr>
                    <th>Poster</th>
                    <th>Movie ID</th>
                    <th>Title</th>
                    <th>Duration (hours)</th>
                    <th>Release Date</th>
                    <th>Description</th>
                    <th>Genre</th>
                    <th>Director</th>
                    <th>Age Rating</th>
                    <th>Star Rating</th>
                    <th>Status</th>
                </tr>
            
            <%
                for (String[] field_rg : records_rg){
            %>
            
            <tr>
                <td><img src="<%= request.getAttribute("path") + field_rg[10] %>" width="135" height="200"></td>
                <td><%= field_rg[0] %></td>
                <td><%= field_rg[1] %></td>
                <td><%= field_rg[2] %></td>
                <td><%= field_rg[3] %></td>
                <td><%= field_rg[4] %></td>
                <td><%= field_rg[5] %></td>
                <td><%= field_rg[6] %></td>
                <td><%= field_rg[7] %></td>
                <td><%= field_rg[8] %></td>
                <td><%= field_rg[9] %></td>
            </tr>
            
            <%
                }
            %>
            
            </table>

        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    </body>
</html>