<%-- 
    Document   : index
    Created on : Apr 7, 2017, 2:02:43 PM
    Author     : yuenyauli2
--%>
<%@page import="database.RefundDB"%>
<%@page import="database.BookDB"%>
<%@page import="database.SalesDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>forWORDS</title>
    </head>
    <body>
            <!-- Header -->
    <jsp:include page="header.jsp"/>
    
    
   
        <h1>Hello World!</h1>
        <a href="./CategoryController"> abcde</a>
        <br>
        <%=BookDB.getPrice(SalesDB.getBookID(RefundDB.getSalesID(8)))%>
        <br>
        <%=BookDB.getPrice(SalesDB.getBookID(15))%>
        <br>
        <%=BookDB.getPrice(47)%>
     <!-- Footer -->
    <jsp:include page="footer.jsp"/> 
    </body>
</html>
