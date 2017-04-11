<%-- 
    Document   : createUser
    Created on : Apr 7, 2017, 4:26:25 PM
    Author     : ktkeung2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store - Create Account</title>
    </head>
    <body>
        <h1>Book Store - Create Account</h1>
        <form action="Signup.do" method="post">
            <p>Username: </p><input name="username" type="text" />
            <p>password: </p><input name="password" type="password" />
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>