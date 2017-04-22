<%-- 
    Document   : myAccount
    Created on : Apr 16, 2017, 8:10:42 PM
    Author     : Kamtso
--%>
<%@page import="database.BookDB"%>
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
            .bold{
                font-weight:bold;
                text-align: right;
                width:20%;
            }
            td{
                width:30%;
                text-align: center;
            }
        </style>
        <title>My Account</title>
    </head>
    <body>
        <!-- Header -->
        <jsp:include page="header.jsp"/>



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
                if (rs.next()) {
                    email = rs.getString("email");
                    money = rs.getInt("money");
                    LP = rs.getInt("loyalPoint");
                }
        %>
        <h1>My Account Information</h1>
        <table class ="table-striped">
            <tr>
                <td class="bold">Username:</td>
                <td><%=user.getUsername()%></td>
            </tr>
            <tr>
                <td class="bold">Email:</td>
                <td><%=email%></td>
            </tr>
            <tr>
                <td class="bold">Money:</td>
                <td><%=money%></td>
            </tr>
            <tr>
                <td class="bold">Loyal Point:</td>
                <td><%=LP%></td>
            </tr>
            <tr>
                <td class="bold">Role:</td>
                <td><%=user.getRole()%></td>
            </tr>
        </table>
        <h1>Purchase history</h1>
        <table class="shop_table">
            <tr>
                <th>Date</th>
                <th>Item</th>
                <th>Quantity</th>
                <th>Refund</th>
            </tr>
            <tr>
            <form action="./CheckOut" method="post">
                <input type="hidden" name="action" value="refund">
                <%
                    preQueryStatement = "SELECT * FROM [SalesLog] WHERE username=? order by Date desc";
                    pStmnt = connection.prepareStatement(preQueryStatement);
                    pStmnt.setString(1, user.getUsername());
                    rs = pStmnt.executeQuery();
                    Timestamp Date = null;
                    int BookID = 0;
                    int quantity = 0;
                    String refund = null;
                    while (rs.next()) {
                        Date = rs.getTimestamp("Date");
                        BookID = rs.getInt("Book_Id");
                        quantity = rs.getInt("Quantity");
                        refund = rs.getString("Refundable");
                %>
                <tr>
                    <td><%=Date%></td>
                    <td><%=BookDB.getBookName(BookID)%></td>
                    <td><%=quantity%></td>
                    <%
                        if (refund.equals("Y")) {
                    %>
                    <td><a class="btn btn-default" href="./index.jsp" role=\"button\">Refund</a></td>
                    <%
                    } else {
                    %>
                    <td>N/A</td>
                    <%
                        }
                    %>
                </tr>
                <%
                    }
                %>

            </form>
            </tr>
            </form>
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
