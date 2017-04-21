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
        <title><%=aBook.getBook_Name()%> - forWORDS</title>
    </head>
    <body>
            <!-- Header -->
    <jsp:include page="header.jsp"/>
    <style>
        #image{
            width: 30%;
            height: auto;
            float: left;
            margin: 1em;
        }
        #title{
            font-size: 25px;
        }
        #detail{
            margin:1em;
        }
        .submit>a{
            background: #e0f3fa; /* Old browsers */
            background: -moz-linear-gradient(top,  #e0f3fa 0%, #d8f0fc 16%, #b6dffd 100%); /* FF3.6-15 */
            background: -webkit-linear-gradient(top,  #e0f3fa 0%,#d8f0fc 16%,#b6dffd 100%); /* Chrome10-25,Safari5.1-6 */
            background: linear-gradient(to bottom,  #e0f3fa 0%,#d8f0fc 16%,#b6dffd 100%); /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
            filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e0f3fa', endColorstr='#b6dffd',GradientType=0 ); /* IE6-9 */
        }
    </style>
    
   
   
    <div class="maincontent-area">
        <div><input class="btn btn-default" type="button" value="Back" onClick="history.go(-1)"></div>
        <div id="image" class="col-sm-3">
            <img class="singleBkImg" src="image/<%=aBook.getImg_File_Name()%>" style="  display: block;object-fit: cover;"/>
        </div>
        <div id="detail" class="col-sm-7">
            <div id="title"><%=aBook.getBook_Name()%></div>
             <p>by <b><%=aBook.getAuthor()%> </b></p>
            <p><b>Published By: </b> <%=aBook.getPress()%> </p>
            <p id="description"><%=aBook.getDescription()%></p>
            <p style="color:#b33404;font-size: 30px;"><b>$</b><%=aBook.getPrice()%> </p>
            <form id="aForm" class="col-md-5 form-horizontal" name="addBookForm" class="form-horizontal" method="POST" action="./CartController?bookID=<%=aBook.getID()%>">
                <div class="form-group">
                  <label for="name" class="col-sm-4 control-label">Quantity</label>
                  <div class="col-sm-7">
                    <select type="number" class="form-control" id="quantity" value="1" name="quantity">
                        <%
                            for (int i =1;i<21;i++)
                            {
                                if(i==1){
                                %>
                                    <option selected="selected"><%=i%></option>
                                <%
                                }
                                else{
                                %>
                                    <option><%=i%></option>
                                <%
                                    }
                            }
                        %>
                    <select>
                  </div>
                </div>
            </form>
            <div class="submit col-sm-3">
                <a class="btn btn-default" href="" role="button">Buy</a>
            </div>
            <div class="submit col-sm-4">        
                <a class="btn btn-default" href="javascript:{}" onclick="document.getElementById('aForm').submit(); return false;" role="button"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
            </div> 
        </div>
    </div>
    <!-- Footer -->
    <jsp:include page="footer.jsp"/> 
    </body>
</html>
