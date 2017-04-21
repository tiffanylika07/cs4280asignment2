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
    <%
        Book aBook = (Book) request.getAttribute("requestedBook");

     %> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Book - forWORDS</title>
    </head>
    <body>
            <!-- Header -->
    <jsp:include page="header.jsp"/>
    
    
   
   
    <div class="maincontent-area">
    <h2>Edit Book</h2>
    <form class="form-horizontal" method="POST" action="./AdminController?action=editBook">
            <div class="form-group">
                <label for="inputID" class="col-sm-2 control-label">ID</label>
                <div class="col-sm-2">
                  <input type="number" class="form-control" id="inputID" name="bookID" value="<%= aBook.getID()%>" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
              <label for="name" class="col-sm-2 control-label">Name</label>
              <div class="col-sm-6">
                <input type="text" class="form-control" id="name"  name="bookName" value="<%=aBook.getBook_Name()%>">
              </div>
            </div>
            <div class="form-group">
                <label for="author" class="col-sm-2 control-label">Author</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" id="author" name="Author" value="<%=aBook.getAuthor() %>">
                </div>
            </div>
            <div class="form-group">
                <label for="price" class="col-sm-2 control-label">Price($)</label>
                <div class="col-sm-2">
                  <input type="number" class="form-control" id="price"  name="Price" value="<%=aBook.getPrice()%>">
                </div>
            </div>
            <div class="form-group">
                <label for="press" class="col-sm-2 control-label">Press</label>
                <div class="col-sm-6">
                  <input type="text" class="form-control" id="Press" name="Press" value="<%=aBook.getPress()%>">
                </div>
            </div>     
            <div class="form-group">
                <label for="description" class="col-sm-2 control-label">Description</label>
                <div class="col-sm-8">
                  <input type="text" class="form-control" id="description"  name="Description" value="<%=aBook.getDescription()%>">
                </div>
            </div>
            <div class="form-group">
                <label for="loyalty" class="col-sm-2 control-label">Loyalty Points</label>
                <div class="col-sm-3">
                  <input type="number" class="form-control" id="loyalty" name="Loyalty_Points" value="<%=aBook.getLoyalty_Point()%>">
                </div>
            </div>
            <div class="form-group">
                <label for="category" class="col-sm-2 control-label">Category</label>
                <div class="col-sm-2">
                  <input type="number" class="form-control" id="categoy"  name="Category" value="<%=aBook.getCategory_ID() %>" min="1" max="6">
                </div>
            </div>
            <div class="form-group">
              <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Save</button>
              </div>
            </div>
</form>
        <a class="btn btn-default" href="./AdminController?action=manageBooks" role="button">Back to Book List</a>
    </div>
    <!-- Footer -->
    <jsp:include page="footer.jsp"/> 
    </body>
</html>
