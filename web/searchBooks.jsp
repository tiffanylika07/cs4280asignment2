<%-- 
    Document   : index
    Created on : Apr 7, 2017, 2:02:43 PM
    Author     : yuenyauli2
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
        <title>Search Books - forWORDS</title>
    </head>
    <body>
    <!-- Header -->
    <jsp:include page="header.jsp"/>
    
    
   

    <div class="maincontent-area">
        <div style="margin-left: auto; margin-right: auto; width:70%;">
    <form action="./BookController?action=search" method="post">
        <div class="input-group input-group-lg" >
              <input type="text" class="form-control"  name="search" placeholder="Search for...">
              <span class="input-group-btn">
                 <button class="btn btn-default" type="submit" >Search</button>
              </span>
            </div><!-- /input-group -->
        </div><!-- /.col-lg-6 -->  
    </form>
    </div>
    <!-- Footer -->
    <jsp:include page="footer.jsp"/> 
    </body>
</html>
