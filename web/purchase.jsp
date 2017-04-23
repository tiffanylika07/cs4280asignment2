<%-- 
    Document   : createUser
    Created on : Apr 7, 2017, 4:26:25 PM
    Author     : ktkeung2
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javabean.CartObject"%>
<%@page import="javabean.Book"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store - Create Account</title>
    </head>
    <body>
        <style>
    .creditCardForm {
    max-width: 700px;
    background-color: #fff;
    overflow: hidden;
    color: #4c4e56;
}

.creditCardForm label {
    width: 100%;
    margin-bottom: 10px;
}

.creditCardForm .heading h1 {
    text-align: center;
    font-family: 'Open Sans', sans-serif;
    color: #4c4e56;
}

.creditCardForm .payment {
    float: left;
    font-size: 18px;
    padding: 10px 25px;
    margin-top: 20px;
    position: relative;
}

.creditCardForm .payment .form-group {
    float: left;
    margin-bottom: 15px;
}

.creditCardForm .payment .form-control {
    line-height: 30px;
    height: auto;
    padding: 0 16px;
}

.creditCardForm .owner {
    width: 63%;
    margin-right: 10px;
}

.creditCardForm .CVV {
    width: 35%;
}

.creditCardForm #card-number-field {
    width: 100%;
}

.creditCardForm #expiration-date {
    width: 49%;
}

.creditCardForm #credit_cards {
    width: 50%;
    margin-top: 25px;
    text-align: right;
}

.creditCardForm #pay-now {
    width: 100%;
    margin-top: 25px;
}

.creditCardForm .payment .btn {
    background-color: #2ec4a5;
    color: white;
}

.creditCardForm .payment select {
    padding: 10px;
    margin-right: 15px;
}

.transparent {
    opacity: 0.2;
}
</style>
        <!-- Header -->
        <jsp:include page="header.jsp"/>
    
        
       
            <center>
        <h1>Order Confirmation</h1>
        <div class="creditCardForm">
            <div class="payment">
                <% if(request.getAttribute("action").equals("single")){ %>
                <form method="POST" action="CheckOut?action=single">
                <% }else{ %>
                <form method="POST" action="CheckOut?action=pay">
                <%}%>
                    <div class="form-group owner">
                        <label for="owner">Owner</label>
                        <input type="text" class="form-control" id="owner">
                    </div>
                    <div class="form-group CVV">
                        <label for="cvv">CVV</label>
                        <input type="text" class="form-control" id="cvv">
                    </div>
                    <div class="form-group" id="card-number-field">
                        <label for="cardNumber">Card Number</label>
                        <input type="text" class="form-control" id="cardNumber">
                    </div>
                    <div class="form-group" id="expiration-date">
                        <label>Expiration Date</label>
                        <select>
                            <option value="01">January</option>
                            <option value="02">February </option>
                            <option value="03">March</option>
                            <option value="04">April</option>
                            <option value="05">May</option>
                            <option value="06">June</option>
                            <option value="07">July</option>
                            <option value="08">August</option>
                            <option value="09">September</option>
                            <option value="10">October</option>
                            <option value="11">November</option>
                            <option value="12">December</option>
                        </select>
                        <select>
                            <option value="16"> 2016</option>
                            <option value="17"> 2017</option>
                            <option value="18"> 2018</option>
                            <option value="19"> 2019</option>
                            <option value="20"> 2020</option>
                            <option value="21"> 2021</option>
                        </select>
                    </div>
                    <div class="form-group" id="pay-now">
                        <button type="submit" class="btn btn-default" id="confirm-purchase">Confirm</button>
                    </div>
                </form>
            </div>
        </div>
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
                if(request.getAttribute("action").equals("single")){
                    cartList = (ArrayList<CartObject>) session.getAttribute("singlePurchase");
                }
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