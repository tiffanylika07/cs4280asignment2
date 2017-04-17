<%-- 
    Document   : menu
    Created on : Apr 7, 2017, 4:09:07 PM
    Author     : yuenyauli2
--%>

<%@page import="javabean.User"%>

<%
    String uri = request.getRequestURI();
    String[] target = uri.substring(uri.lastIndexOf("/") + 1).split("\\W");
    String pageName = target[0];

    boolean isAdmin = false;
    User user = null;
    if (session.getAttribute("userInfo") != null) {
        user = (User) session.getAttribute("userInfo");
        if ("admin".equals(user.getRole())) {
            isAdmin = true;
        }
    }
%>

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
                    <li class="<%=(pageName.equals("viewBooksByCategory") || pageName.equals("searchBooks")) ? "active" : ""%>"><a href="./BookController?action=search">Books</a></li>
                </ul>                       
            </div>                
        </div>
    </div>
</div>