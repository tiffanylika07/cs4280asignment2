<%-- 
    Document   : cart
    Created on : 2017年4月17日, 下午07:26:24
    Author     : Kamtso
--%>
<%@page import="javabean.cartObject"%>
<%@page import="javabean.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javabean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store - Cart</title>
    </head>
    <body>
        <!-- Header -->
        <jsp:include page="header.jsp"/>

        <!-- Main Menu -->
        <jsp:include page="menu.jsp"/>
    <center>
        <h1>Book Store - Cart</h1>
        <table class="shop_table">
            <tr>
            <th>Item</th>
            <th>Quantity</th>
            <th>Price</th>
            </tr>
            <%
                ArrayList<cartObject> cartList = (ArrayList<cartObject>) session.getAttribute("cart");

                float total = 0;
                if (cartList != null && cartList.size() > 0) {
                    for (cartObject cartObj : cartList) {
            %>
            <tr>
                <td class="shopping-item"><%=cartObj.getBook().getBook_Name()%></td>
                <td class="shopping-item"><%=cartObj.getQuantity()%></td>
                <td class="shopping-item"><%=cartObj.getBook().getPrice()%></td>
            </tr>
            <%
                    }
                }
            %>
            <tr>
                <td colspan="2">&nbsp;</td>
                <td>Sub-total: <%=total%></td>
            </tr>
        </table>
    </center>
    <jsp:include page="footer.jsp"/>
</body>
</html>
