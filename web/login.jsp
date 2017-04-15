<%-- 
    Document   : login
    Created on : Apr 7, 2017, 1:55:29 PM
    Author     : ktkeung2
--%>
<%@page import="javabean.Book"%>
<%@page import="javabean.Category"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!-- Google Fonts -->
<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- Font Awesome -->
<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="css/owl.carousel.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/responsive.css">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store - Login</title>
    </head>
    <body>
    <!-- Header -->
    <jsp:include page="header.jsp"/>
    
    <!-- Main Menu -->
    <jsp:include page="menu.jsp"/>
   
    <div class="maincontent-area">
        <div class="loginForm">
            <h1>Login</h1>
            <form action="login.do" method="post">
                <p>Username: <input name="username" type="text" /></p>
                <p>password: <input name="password" type="password" /></p>
                <br>
                <input type="submit" value="Login"/>
    <!--            <a href="./createUser.jsp">Create Account</a>-->
            </form>
        </div>
    </div>
        <!-- Footer -->
    <jsp:include page="footer.jsp"/> 
    </body>
</html>
