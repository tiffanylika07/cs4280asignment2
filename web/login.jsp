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
        <h1>Book Store - Login</h1>
        <form action="login.do" method="post">
            <p>Username: </p><input name="username" type="text" />
            <p>password: </p><input name="password" type="password" />
            <input type="submit" value="Submit"/>
            <a href="./createUser.jsp">Create Account</a>
        </form>
    </body>
</html>
