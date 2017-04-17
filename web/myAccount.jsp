<%-- 
    Document   : myAccount
    Created on : Apr 16, 2017, 8:10:42 PM
    Author     : Kamtso
--%>
<%@page import="java.sql.*"%>
<%@page import="javabean.User"%>
<%@page import="database.ConnectionUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            table{
                font-size:20px;
            }
        </style>
        <title>My Account</title>
    </head>
    <body>
        <!-- Header -->
        <jsp:include page="header.jsp"/>

        <!-- Main Menu -->
        <jsp:include page="menu.jsp"/>
        <center>
        <%
            User user = (User) session.getAttribute("userInfo");
            if (user != null) {
                Connection connection = ConnectionUtil.getConnection();
                String preQueryStatement = "SELECT * FROM [USER] WHERE username=?";
                PreparedStatement pStmnt = connection.prepareStatement(preQueryStatement);
                pStmnt.setString(1, user.getUsername());
                ResultSet rs = pStmnt.executeQuery();
                String email = null;
                float money = 0;
                int LP = 0;
                if (rs.next()){
                    email = rs.getString("email");
                    money = rs.getFloat("money"); 
                    LP = rs.getInt("loyalPoint"); 
                }
        %>
        <h1>My Account Information</h1>
        <table>
            <tr>
                <td>Username:</td>
                <td><%=user.getUsername()%></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><%=email%></td>
            </tr>
            <tr>
                <td>Money:</td>
                <td><%=money%></td>
            </tr>
            <tr>
                <td>Loyal Point:</td>
                <td><%=LP%></td>
            </tr>
            <tr>
                <td>Role:</td>
                <td><%=user.getRole()%></td>
            </tr>
        </table>
        <%
        } else {
        %>
        <h1>You are not logged in.</h1>
        <%
            }
        %>
        </center>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
