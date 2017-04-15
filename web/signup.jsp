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
    <jsp:include page="header.jsp"/>
    <center>
        <h1>Book Store - Create Account</h1>
        <form action="Signup.do" method="post">
            <p>Username: <input name="username" type="text" /></p>
            <p>password: <input name="password" type="password" /></p>
            <input type="submit" value="Signup"/>
        </form>
    </center>
    <jsp:include page="footer.jsp"/>
</body>
</html>