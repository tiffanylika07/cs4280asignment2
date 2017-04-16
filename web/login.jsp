<%-- 
    Document   : login
    Created on : Apr 7, 2017, 1:55:29 PM
    Author     : ktkeung2
--%>
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
            <center>
            <h1>Login</h1>
            <form action="login.do" method="post">
                <p>Username: <input name="username" type="text" /></p>
                <p>password: <input name="password" type="password" /></p>
                <br>
                <input type="submit" value="Login"/>
    <!--            <a href="./createUser.jsp">Create Account</a>-->
            </form>
            </center>
        </div>
    </div>
        <!-- Footer -->
    <jsp:include page="footer.jsp"/> 
    </body>
</html>
