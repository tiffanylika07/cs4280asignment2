<%-- 
    Document   : header
    Created on : Apr 7, 2017, 4:21:09 PM
    Author     : yuenyauli2
--%>
<!-- Google Fonts -->
<link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Bad+Script" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Droid+Sans" rel="stylesheet">
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- Font Awesome -->
<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="css/owl.carousel.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/responsive.css">
<%@page import="javabean.User"%>

<% 
    String uri = request.getRequestURI();
    String[] target = uri.substring(uri.lastIndexOf("/") + 1).split("\\W");
    String pageName = target[0];

    boolean isLogin = false;
    boolean isAdmin = false;
    User user = null;
    if (session.getAttribute("userInfo") != null) {
        isLogin = true;
        user = (User) session.getAttribute("userInfo");
        if ("admin".equals(user.getRole())) {
            isAdmin = true;
        }
    }
%>

<script>
    var currentUrl = document.baseURI;
    var lastSegment;
    do {
        lastSegment = currentUrl.split('/').pop();
    } while (lastSegment == null);

    function onClickDirectPage(url) {
        document.location.href = url;
    }
</script>

<div class="header-area">
    <div class="row">
        <div class="note">
            This web site exists to fulfill the coursework requirement of CS4280. Do not use your real personal data as input.     
        </div>
    </div>
    
   <div class="mainmenu-area">
    <div class="container">
        <div class="row">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div> 
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="<%=pageName.equals("index") ? "active" : ""%>"><a href="index.jsp">Home</a></li>
                    <li class="<%=(pageName.equals("viewCategory")) ? "active" : ""%>"><a href="./CategoryController">Category</a></li>
                    <li class="<%=(pageName.equals("viewBooksList") || pageName.equals("searchBooks")||pageName.equals("viewSingleBook")) ? "active" : ""%>"><a href="./BookController?action=viewSearch">Books</a></li>
                    <% if (isAdmin) { %>
                        <li class="<%=(pageName.equals("adminMenu")||pageName.equals("manageBookList")||pageName.equals("editBook")) ? "active" : ""%>"><a href="./AdminController?action=main">Admin</a></li>
                    <% } %>
                </ul>   
                
                 <ul class="nav navbar-nav navbar-right">
                         <% if (isLogin) { %>
                         <li><i class='glyphicon glyphicon-user'></i> Hi <%=user.getUsername()%> </li>     
                        <li><a href="MyAccount"><i class="fa fa-user"></i> My Account</a></li>
                        <li><a href="cart.jsp"><i class="glyphicon glyphicon-shopping-cart"></i> My Cart</a></li>
<!--                    </ul>
                </div>
            </div> 
        
            <div class="col-md-4">
                <div class="header-right">
                    <ul class="list-unstyled list-inline">-->
                                  
                            <li><a href="login.do?action=logout"><i class="glyphicon glyphicon-log-in"></i> Logout</a></li>
                        <% } else { %>
                            <li><a href="signup.jsp"><i class="glyphicon glyphicon-pencil"></i> Sign up</a></li>
                            <li><a href="login.jsp"><i class="glyphicon glyphicon-log-in"></i> Login</a></li>
                        <% } %>
                        
                    </ul>
            </div>                
        </div>
    </div>
</div>
</div> 
    <style>
        #title{
            font-family: 'Bad Script', cursive;
        }
        .logo h1{
            padding-left: 1em;
        }
    </style>
<!-- Site Branding Area -->
<div class="site-branding-area">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="logo">
                    <h1 id ="title"><a href="./index.jsp">forWORDS</a></h1>
                </div>
            </div>

            <div class="col-sm-6">
                <div class="shopping-item">
                    <a href="cart.jsp"><span class="fa fa-shopping-cart"> Cart</span></a>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End site branding area -->
