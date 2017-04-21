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
    
    
   
   
    <div class="maincontent-area">
        <div class="loginForm">
            <center>
            <h1>Login</h1>
            <form class="form-horizontal" action="login.do" method="post">
                <div class="form-group">
                    <label for="uName" class="col-sm-5 control-label">Username:</label>
                    <div class="col-sm-5">
                      <input type="text" class="form-control" id="uName" name="username" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="pw" class="col-sm-5 control-label">Password:</label>
                    <div class="col-sm-5">
                      <input type="password" class="form-control" id="pw" name="password" >
                    </div>
                </div>
                <input type="submit" value="Login"/>
            </form>
            
            
            </center>
        </div>
    </div>
        <!-- Footer -->
    <jsp:include page="footer.jsp"/> 
    </body>
</html>
