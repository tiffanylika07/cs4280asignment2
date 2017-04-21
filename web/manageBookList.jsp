<%-- 
    Document   : index
    Created on : Apr 7, 2017, 2:02:43 PM
    Author     : yuenyauli2
--%>

<%@page import="javabean.Book"%>
<%@page import="javabean.Category"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mange Book List -forWORDS</title>
    </head>
    <body>
    <!-- Header -->
    <jsp:include page="header.jsp"/>
    
    
   
    <% 
     String msg = (String)request.getAttribute("msg");//means you have to send the message as a parameter

    %>

    <%     
     %>
    <div class="maincontent-area">
 
    <script type="text/javascript">

    function openPopup() {
        var w = 450;
        var h = 450;
        var left = (screen.width/2)-(w/2);
        var top = (screen.height/2)-(h/2);
        var strWindowFeatures = 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left;

     return  window.open("addBook.jsp", "", strWindowFeatures);
    }
    </script>
    <div class="content_container">
        <p>${msg}</p>
        <a class="btn btn-default" href="javaScript:{openPopup();}" role="button">Add Book</a>
        <table class="table table-striped table-hover">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Author</th>
                <th>Price($)</th>
                <th>Press</th>
                <th>Description</th>
                <th>Loyalty Points</th>
                <th>Category ID</th>
                <th></th>
                <th></th>
            </tr>
            <%
            List<Book> list = (List<Book>) request.getAttribute("bookList");
            if(list!=null){
                for(Book k :list){
                    out.println("<tr>");
                    out.println("<td>"+k.getID()+" </td>");
                    out.println("<td class=\"bookName\"  >"+k.getBook_Name()+" </td>");
                    out.println("<td class=\"authorName\"  >"+k.getAuthor()+" </td>");
                    out.println("<td class=\"bookPrice\"  >$"+k.getPrice()+" </td>");
                    out.println("<td class=\"pressName\"  >"+k.getPress()+" </td>");
                    out.println("<td>"+k.getDescription()+"</td>");
                    out.println("<td class=\"loyaltyPoint\"  >"+k.getLoyalty_Point()+" </td>");
                    out.println("<td class=\"loyaltyPoint\"  >"+k.getCategory_ID()+"</td>");
                    out.println("<td><a class=\"btn btn-default\" href=\"./AdminController?action=getBook&bookID="+k.getID()+"\" role=\"button\">Edit</a></td>");
                    out.println("<td><a class=\"btn btn-default\" href=\"./AdminController?action=removeBook&bookID="+k.getID()+"\" role=\"button\"  onclick=\"return confirm('Are you sure you want to delete this book?')\">Remove</a></td>");
                    out.println("<tr>");
                }
            }
        %>
        </table>

    </div>       
    </div>
    <!-- Footer -->
    <jsp:include page="footer.jsp"/> 
    </body>
</html>
