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
        <title><%=request.getAttribute("categoryName")%> - ABC BookStore</title>
    </head>
    <body>
            <!-- Header -->
    <jsp:include page="header.jsp"/>
    
    <!-- Main Menu -->
    <jsp:include page="menu.jsp"/>
   
    <div class="maincontent-area">
    <h2>Books</h2>
    <div class="content_container">
        <div class="w-clearfix">
        <%
           List<Book> list = (List<Book>) request.getAttribute("bookList");
           if(list!=null){
                for(Book k :list){
                    out.println("<div class=\"_60tile f_left\">");
                    out.println("<a href=\"./BookController?action=single&bookID="+k.getID()+"\">");
                    out.println("<div><img  src=\"image/"+k.getImg_File_Name()+"\" style=\"width:50%;\"/> </div>");
                    out.println("<div>");
                    out.println("<span class=\"bookName\"  >"+k.getBook_Name()+" </span></a>");
                    out.println("<span class=\"authorName\"  >"+k.getAuthor()+" </span>");
                    out.println("<span class=\"pressName\"  >"+k.getPress()+" </span>");
                    out.println("<span class=\"bookPrice\"  >$"+k.getPrice()+" </span>");
                    out.println("<span class=\"loyaltyPoint\"  >"+k.getLoyalty_Point()+" </span>");
                    out.println("</div>");
                    out.println("<br/></div>");
                }
           }
        %>
        </div>
    </div>
                
    </div>
    <!-- Footer -->
    <jsp:include page="footer.jsp"/> 
    </body>
</html>
