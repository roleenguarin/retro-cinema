<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="general.css">
        <link rel="stylesheet" href="HomeCSS.css">
    </head>
  
    <body  id="Home">
        <div class="row d-flex justify-content-center" id="header">
            <div class="d-flex flex-wrap col-2 justify-content-center align-items-center">
                <img id="headLogo" src="Movie Posters<%= File.separator%>Cinema.png" height="80" alt="cinema logo"/>
            </div>
            <div class="d-flex flex-wrap col-1 justify-content-center align-items-end">
                <h2 id="head">Home</h2>
            </div>
            <div class="d-flex flex-wrap col-1 justify-content-center align-items-end">
                <h2 id="head">Movies</h2>
            </div>
            <div class="d-flex flex-wrap col-1 justify-content-center align-items-end">
                <h2 id="head">Cinemas</h2>
            </div>
            <div class="d-flex flex-wrap col-1 justify-content-center align-items-end">
                <h2 id="head">Events</h2>
            </div>
            <!--<div class="d-flex flex-wrap col-1 justify-content-center align-items-end">
                <h2 id="head"><a href="MenuServlet?Menu=Home">Menu</h2>
            </div>-->
            <div class="d-flex flex-wrap col-1 justify-content-center align-items-end">
                <h3 id="head">Welcome!</h3>
            </div>
        </div>
        
            
        <div class="d-flex col justify-content-center" id="MainBG">
            <div class="d-flex row justify-content-center" id="MainLogin">
                <div id="LoginForm">
                    <form method="POST" action='LoginServlet'>
                        <div class="row">
                            <div class="col text-center">
                                <h1 class="display-3 ">Login</h1>  
                                <!--some text here that will change pag invalid un or pw-->
                                <p  style="color:gray;"><%if (request.getAttribute("loggedin") != null){%> Invalid username or password. <%}%></p>
                            </div>                          
                        </div>

                        <div class="row">
                            <div class="col">
                                <label class="form-label">Email</label>
                                <input name="email" type="text" class="form-control">                  
                            </div>                       
                        </div>

                        <div class="row">
                            <div class="col">
                                <label class="form-label">Password</label>
                                <input name="password" type="password" class="form-control">                  
                            </div>                       
                        </div>

                        <div class="d-flex flex-row-reverse">
                            <input id="login" type="submit" class="btn btn-primary" value="Login">
                            <a href="SignUp.jsp"><p id="loginbtn">Sign Up</p></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        

        <div class="row d-flex justify-content-center" id="footer" style="background-img: url(<%= session.getAttribute("path")%>FooterBG.png);">
            <div class="d-flex flex-column flex-wrap col-1 justify-content-center align-items-center">
                <p id="foot">Home</p>
                <p id="foot">Movies</p>
                <p></p>
            </div>
            <div class="d-flex flex-column flex-wrap col-1 justify-content-center align-items-center">
                <p id="foot">Cinemas</p>
                <p id="foot">Events</p>
                <p></p>
            </div>
            <div class="d-flex flex-column flex-wrap col-1 justify-content-center align-items-center">
                <p id="foot">Contact Us</p>
                <p id="foot">mobile number</p>
                <p id="foot">landline number</p>
            </div>
            <div class="d-flex flex-wrap col-1 justify-content-center align-items-center">
                <img src="Movie Posters<%= File.separator%>Cinema.png" width="auto" height="90" alt="cinema logo"/>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>