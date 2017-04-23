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
        <title><%=request.getAttribute("categoryName")%> - forWORDS</title>
    </head>
    <body>
            <!-- Header -->
    <jsp:include page="header.jsp"/>
    
    
   
   
    <div class="maincontent-area">
        <div class="content_container">
        <% if(request.getAttribute("action") == "search"){
            out.println("<p class=\"display\">Search Results of: <span class=\"keywords\">"+request.getAttribute("keywords")+"<span></p>");
        }
        else if(request.getAttribute("action") == "category"){
            out.println("<p class=\"display\">Category: <span class=\"keywords\">"+request.getAttribute("categoryName")+"<span></p>");
        }
        %>
            <div id="body">
                <div class="inner">
                        <%
                            ArrayList<Book> list = (ArrayList<Book>) request.getAttribute("bookList");
                            float total = 0;
                            if (list != null && list.size() > 0) {
                                for (Book book : list) {
                        %>
                        <div class="leftbox">
                            <h3><%= book.getBook_Name()%></h3>
                            <img src="image/<%=book.getImg_File_Name()%>" width="93" height="95" alt="photo 1" class="left" />
                            <p><b>Author</b> <%= book.getAuthor()%></p>
                            <p><b>Press</b> <b><%= book.getPress()%></b> </p>
                            <p><b>Price:</b> <b><%= book.getPrice()%></b> </p>
                            <p class="readmore"><a href="./BookController?action=single&bookID=<%=book.getID()%>">READ MORE</a></p>
                        </div>
                        <%
                                }
                            }
                        %>
                        
                </div>
            </div>
        </div>
    </div>
    <!-- Footer -->
    <jsp:include page="footer.jsp"/> 
    </body>
</html>
