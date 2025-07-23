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
            <div class="d-flex flex-wrap col-1 justify-content-center align-items-end">
                <h3 id="head">Welcome!</h3>
            </div>
        </div>
        
            
        <div class="d-flex col justify-content-center" id="MainBG">
            <div class="d-flex row justify-content-center" id="MainLogin">
                <div id="LoginForm">
                    <form method="POST" action='RegisterServlet' enctype='multipart/form-data'>
                        <div class="row">
                            <div class="col text-center">
                                <h1 class="display-3 ">Sign Up</h1>                  
                            </div>                          
                        </div>

                        <div class="row">
                            <div class="col">
                                <label class="form-label">First Name</label>
                                <input name="first" type="text" class="form-control">                  
                            </div>                          
                            <div class="col">
                                <label class="form-label">Last Name</label>
                                <input name="last" type="text" class="form-control">                  
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
                                <label class="form-label">Phone</label>
                                <input name="contact" type="text" class="form-control">                  
                            </div>                       
                        </div>

                        <div class="row">
                            <div class="col">
                                <label class="form-label">Password</label>
                                <input name="password" type="password" class="form-control">                  
                            </div>                       
                        </div>

                        <div class="row">
                            <div class="col">
                                <label class="form-label">Confirm Password</label>
                                <input name="confirm password" type="password" class="form-control">                  
                            </div>                       
                        </div>

                        <div class="row">
                            <div class="col">
                                <label class="form-label">Role</label>
                                <select name = "role" type="text" class="form-control">
                                    <option> Admin </option>
                                    <option> Employee </option>
                                    <option> Customer </option>
                                </select>                  
                            </div>                       
                        </div>

                        <div class="d-flex flex-row-reverse align-items-center">
                            <input id="signupbtn" type="submit" class="btn btn-primary" value="Sign Up">
                            <a href="Login.jsp"><p id="loginbtn">Login</p></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        

        <div class="row d-flex justify-content-center" id="footer" style="background-img: url(<%= session.getAttribute("path")%>FooterBG.png);">
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
                <img src="<%= session.getAttribute("path")%>Cinema.png" width="auto" height="90" alt="cinema logo"/>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</html>