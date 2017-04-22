<%-- 
    Document   : index
    Created on : Apr 7, 2017, 2:02:43 PM
    Author     : yuenyauli2
--%>
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
    <style>

    @import url(http://fonts.googleapis.com/css?family=Roboto:400,900);

    .module {
      background: 
        linear-gradient(
          rgba(0, 0, 0, 0.6),
          rgba(0, 0, 0, 0.6)
        ),
        url(image/slider_1.jpg);
      background-size: cover;
      width: 100%;
      height: 79vh;
      margin: 10px 0 0 10px;
      position: relative;
      float: left;  
      
    }

    .top h2 {
      color: white;
      margin: 0;
      padding: 20px;
      font-family: 'Roboto', sans-serif;
        font-weight: 900;
        position:absolute;
        top: 30%;
        left: 10%;
        font-size: 50px;
    }

    #storeName{
        color: rgb(255, 83, 129);
        text-shadow: 5px -1px 6px rgba(6, 0, 0, 0.3);
        font-family: 'Bad Script', cursive;
    }
    </style> 
    <div class="content_container">
        <div class="module top">
            <h2>Welcome to <br/><br/><span id="storeName">forWORDS</span></h2>
        </div>
    </div>
     <!-- Footer -->
    <jsp:include page="footer.jsp"/> 
    </body>
</html>
