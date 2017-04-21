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
    <style>
        tbody{
            overflow: scroll;
        }
</style>
    <div class="content_container">
        <p>${msg}</p>
        <a class="btn btn-default" href="javaScript:{openPopup();}" role="button">Add Book</a>
        <table class="table table-striped table-hover">
            <tr>
                <th class="col-sm-1">ID</th>
                <th class="col-sm-1">Name</th>
                <th class="col-sm-1">Author</th>
                <th class="col-sm-1">Price($)</th>
                <th class="col-sm-1">Press</th>
                <th class="col-sm-2">Description</th>
                <th class="col-sm-1">Loyalty Points</th>
                <th class="col-sm-1">Category ID</th>
                <th class="col-sm-1"></th>
                <th class="col-sm-1"></th>
            </tr>
            <%
            List<Book> list = (List<Book>) request.getAttribute("bookList");
            if(list!=null){
                for(Book k :list){
            %>
                    <tr>
                    <td><%=k.getID()%></td>
                    <td><%=k.getBook_Name()%></td>
                    <td><%=k.getAuthor()%></td>
                    <td><%=k.getPrice()%></td>
                    <td><%=k.getPress()%></td>
                    <td><%=k.getDescription()%></td>
                    <td><%=k.getLoyalty_Point()%></td>
                    <td><%=k.getCategory_ID()%></td>
                    <td><a class="btn btn-default" href="./AdminController?action=getBook&bookID=<%=k.getID()%>" role=\"button\">Edit</a></td>
                    <td><a class="btn btn-default" href="./AdminController?action=removeBook&bookID=<%=k.getID()%>" role=\"button\"  onclick=\"return confirm('Are you sure you want to delete this book?')\">Remove</a></td>
                    <tr>
        <%
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
