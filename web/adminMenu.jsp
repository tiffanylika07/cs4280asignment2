<%-- 
    Document   : index
    Created on : Apr 7, 2017, 2:02:43 PM
    Author     : yuenyauli2
--%>

<%@page import="javabean.Category"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin -ABC BookStore</title>
    </head>
    <body>
    <!-- Header -->
    <jsp:include page="header.jsp"/>
   
    <div class="maincontent-area">
    <p style="color: red;">${errorString}</p>
 
    <div class="content_container">
        <a class="btn btn-default" href="./AdminController?action=manageBooks" role="button">Manage Book</a>
        <a class="btn btn-default" href="./AdminController?action=approveRefund" role="button">Manage Refund Request</a>

    </div>       
    </div>
    <!-- Footer -->
    <jsp:include page="footer.jsp"/> 
    </body>
</html>
