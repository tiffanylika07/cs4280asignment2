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
        <!-- Header -->
        <jsp:include page="header.jsp"/>
    
        
       
        <center>
            <div class="maincontent-area">
            <h1>Book Store - Create Account</h1>
            <form class="form-horizontal" action="Signup.do" method="post" style="width:50%;margin:auto;">
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
                <div class="form-group">
                    <label for="email" class="col-sm-5 control-label">Email:</label>
                    <div class="col-sm-5">
                      <input type="text" class="form-control" id="email" name="email" >
                    </div>
                </div>
                <input type="submit" value="Signup"/>
            </form>
            </div>
        </center>
        <jsp:include page="footer.jsp"/>
    </body>
</html>