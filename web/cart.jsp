<%-- 
    Document   : cart
    Created on : 2017年4月17日, 下午07:26:24
    Author     : Kamtso
--%>
<%@page import="javabean.CartObject"%>
<%@page import="javabean.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javabean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store - Cart</title>
        <script>
            function checkForm(form){
                var cashPay = parseInt(form.cashPay.value);
                var LPPay = parseInt(form.LPPay.value);
                var subTotal = parseInt(form.subTotal.value);
//                alert(cashPay+"<br>"+LPPay+"<br>"+subTotal);
                if (cashPay>subTotal){
                    alert("PAY BY CASH Out of range!");
                    form.cashPay.focus();
                } else if (LPPay>subTotal){
                    alert("PAY BY LP Out of range!");
                    form.LPPay.focus();
                } else {
                    form.submit();
                }
                form.reset();
            }
        </script>
    </head>
    <body>
        <!-- Header -->
        <jsp:include page="header.jsp"/>

    <center>
        <h1>Book Store - Cart</h1>
        <table class="shop_table">
            <tr>
                <th>Item</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Pay by money</th>
                <th>Pay by LP</th>
                <th>Sub-total</th>
            </tr>

            <%
                ArrayList<CartObject> cartList = (ArrayList<CartObject>) session.getAttribute("cart");

                float total = 0;
                if (cartList != null && cartList.size() > 0) {
                    for (CartObject cartObj : cartList) {
            %>
            <tr>
            <form action="./CartController" method="post">
                <td><%=cartObj.getBook().getBook_Name()%></td>
                <td><select type="number" class="form-control" id="quantity" value="<%=cartObj.getQuantity()%>" name="quantity" onchange="javascript:checkForm(this.form);">
                        <%
                            for (int i =0;i<21;i++)
                            {
                                if(i==cartObj.getQuantity()){
                                %>
                                    <option selected="selected"><%=cartObj.getQuantity()%></option>
                                <%
                                }
                                else{
                                %>
                                    <option><%=i%></option>
                                <%
                                    }
                            }
                        %>
                    <select></td>
                <td><%=cartObj.getBook().getPrice()%></td>
                <td><input type="text" name="cashPay" value="<%=cartObj.getCashPay()%>" onchange="javascript:checkForm(this.form);"></td>
                <td><input type="text" name="LPPay" value="<%=cartObj.getLPPay()%>" onchange="javascript:checkForm(this.form);"></td>
                <td><%=cartObj.getBook().getPrice() * cartObj.getQuantity()%></td>
                <input type="hidden" name="subTotal" value="<%=cartObj.getBook().getPrice() * cartObj.getQuantity()%>">
                <input type="hidden" name="id" value="<%=cartObj.getBook().getID()%>">
                <input type="hidden" name="action" value="updateCart">
            </form>
            </tr>
            <%
                        total += cartObj.getBook().getPrice() * cartObj.getQuantity();
                    }
                }
            %>
            <tr>
                <td colspan="5">&nbsp;</td>
                <td>Total: <%=total%></td>
            </tr>
            </form>
        </table>
    </center>
    <jsp:include page="footer.jsp"/>
</body>
</html>
