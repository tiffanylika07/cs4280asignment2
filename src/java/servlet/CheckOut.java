/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.ConnectionUtil;
import database.SalesDB;
import database.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabean.CartObject;
import javabean.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kamtso
 */
public class CheckOut extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("action") != null) {
            if (request.getParameter("action").equals("pay")) {
                processCheckOut(request, response);
            }
            else if (request.getParameter("action").equals("single")) {
                processSinglePurchase(request, response);
            }
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/purchase.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    private void processCheckOut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Book Store - CheckOut</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            request.getRequestDispatcher("/header.jsp").include(request, response);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("userInfo");
            int money = 0;
            int LP = 0;

            if (user != null) {
                try {
                    Connection connection = ConnectionUtil.getConnection();
                    String preQueryStatement = "SELECT money,loyalPoint FROM [USER] WHERE username=?";
                    PreparedStatement pStmnt = connection.prepareStatement(preQueryStatement);
                    pStmnt.setString(1, user.getUsername());
                    ResultSet rs = pStmnt.executeQuery();
                    if (rs.next()) {
                        money = rs.getInt("money");
                        LP = rs.getInt("loyalPoint");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CheckOut.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CheckOut.class.getName()).log(Level.SEVERE, null, ex);
                }

                ArrayList<CartObject> cartList = (ArrayList<CartObject>) session.getAttribute("cart");

                int cashSum = 0;
                int LPSum = 0;

                    for (CartObject cartObj : cartList) {
                    //cashSum += cartObj.getCashPay();
                    LPSum += cartObj.getLPPay();
                }

                if (LPSum > LP) {
                    out.println("<h1>Your LP is not enough!</h1>");
                    out.println("<h1>You will be back to cart page in 3 seconds.</h1>");
                    out.println("<meta http-equiv=\"Refresh\" content=\"3;url=./cart.jsp\">");
                } else {
                    out.println("<h1>Sales Record</h1>");
                    out.print("        <table class=\"table-striped\">"
                            + "            <tr>\n"
                            + "                <th>Item</th>\n"
                            + "                <th>Quantity</th>\n"
                            + "                <th>Price</th>\n"
                            + "                <th>Pay by money</th>\n"
                            + "                <th>Pay by LP</th>\n"
                            + "                <th>Sub-total</th>"
                            + "            </tr>\n");
                    UserDB userDB = new UserDB();
                    SalesDB sales = new SalesDB();
                    for (CartObject cartObj : cartList) {
                        boolean refundable = false;
                        if (cartObj.getLPPay() == 0) {
                            refundable = true;
                        }
                        if (userDB.purchase(user.getUsername(), cartObj.getCashPay(), cartObj.getLPPay(),cartObj.getBook().getLoyalty_Point())) {
                            if (sales.addRecord(user.getUsername(), cartObj.getBook().getID(), cartObj.getQuantity(), refundable)) {
                                out.print("            <tr>\n"
                                        + "                <td>" + cartObj.getBook().getBook_Name() + "</td>\n"
                                        + "                <td>" + cartObj.getQuantity() + "</td>\n"
                                        + "                <td>" + cartObj.getBook().getPrice() + "</td>\n"
                                        + "                <td>" + cartObj.getCashPay() + "</td>\n"
                                        + "                <td>" + cartObj.getLPPay() + "</td>\n"
                                        + "                <td>" + cartObj.getQuantity() * cartObj.getBook().getPrice() + "</td>\n"
                                        + "            </tr>\n");
                            }
                        }
                    }
                    int sum = cashSum + LPSum;
                    out.print("            <tr>\n"
                            + "                <td colspan=\"5\">&nbsp;</td>\n"
                            + "                <td>Total: " + sum + "</td>\n"
                            + "            </tr>\n"
                            + "        </table>");
                    session.removeAttribute("cart");
                    out.println("<a class=\"btn btn-default\" href=\"./index.jsp\" role=\"button\">Back to menu</a>");
                }
            } else {
                out.println("<h1>You are not logged in! Please login to continue</h1>");
                out.println("<h1>You will be back to login page in 3 seconds.</h1>");
                out.println("<meta http-equiv=\"Refresh\" content=\"3;url=./login.jsp\">");
            }
            out.println("</center>");
            request.getRequestDispatcher("/footer.jsp").include(request, response);
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private void processSinglePurchase(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Book Store - CheckOut</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<center>");
            request.getRequestDispatcher("/header.jsp").include(request, response);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("userInfo");
            int money = 0;
            int LP = 0;

            if (user != null) {
                try {
                    Connection connection = ConnectionUtil.getConnection();
                    String preQueryStatement = "SELECT money,loyalPoint FROM [USER] WHERE username=?";
                    PreparedStatement pStmnt = connection.prepareStatement(preQueryStatement);
                    pStmnt.setString(1, user.getUsername());
                    ResultSet rs = pStmnt.executeQuery();
                    if (rs.next()) {
                        money = rs.getInt("money");
                        LP = rs.getInt("loyalPoint");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CheckOut.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CheckOut.class.getName()).log(Level.SEVERE, null, ex);
                }

                ArrayList<CartObject> cartList = (ArrayList<CartObject>) session.getAttribute("singlePurchase");

                int cashSum = 0;
                int LPSum = 0;

                    for (CartObject cartObj : cartList) {
                    //cashSum += cartObj.getCashPay();
                    LPSum += cartObj.getLPPay();
                }

                if (LPSum > LP) {
                    out.println("<h1>Your LP is not enough!</h1>");
                    out.println("<h1>You will be back to cart page in 3 seconds.</h1>");
                    out.println("<meta http-equiv=\"Refresh\" content=\"3;url=./cart.jsp\">");
                } else {
                    out.println("<h1>Sales Record</h1>");
                    out.print("        <table class=\"table-striped\">"
                            + "            <tr>\n"
                            + "                <th>Item</th>\n"
                            + "                <th>Quantity</th>\n"
                            + "                <th>Price</th>\n"
                            + "                <th>Pay by money</th>\n"
                            + "                <th>Pay by LP</th>\n"
                            + "                <th>Sub-total</th>"
                            + "            </tr>\n");
                    UserDB userDB = new UserDB();
                    SalesDB sales = new SalesDB();
                    for (CartObject cartObj : cartList) {
                        boolean refundable = false;
                        if (cartObj.getLPPay() == 0) {
                            refundable = true;
                        }
                        if (userDB.purchase(user.getUsername(), cartObj.getCashPay(), cartObj.getLPPay(),cartObj.getBook().getLoyalty_Point())) {
                            if (sales.addRecord(user.getUsername(), cartObj.getBook().getID(), cartObj.getQuantity(), refundable)) {
                                out.print("            <tr>\n"
                                        + "                <td>" + cartObj.getBook().getBook_Name() + "</td>\n"
                                        + "                <td>" + cartObj.getQuantity() + "</td>\n"
                                        + "                <td>" + cartObj.getBook().getPrice() + "</td>\n"
                                        + "                <td>" + cartObj.getCashPay() + "</td>\n"
                                        + "                <td>" + cartObj.getLPPay() + "</td>\n"
                                        + "                <td>" + cartObj.getQuantity() * cartObj.getBook().getPrice() + "</td>\n"
                                        + "            </tr>\n");
                            }
                        }
                    }
                    int sum = cashSum + LPSum;
                    out.print("            <tr>\n"
                            + "                <td colspan=\"5\">&nbsp;</td>\n"
                            + "                <td>Total: " + sum + "</td>\n"
                            + "            </tr>\n"
                            + "        </table>");
                    session.removeAttribute("cart");
                    out.println("<a class=\"btn btn-default\" href=\"./index.jsp\" role=\"button\">Back to menu</a>");
                }
            } else {
                out.println("<h1>You are not logged in! Please login to continue</h1>");
                out.println("<h1>You will be back to login page in 3 seconds.</h1>");
                out.println("<meta http-equiv=\"Refresh\" content=\"3;url=./login.jsp\">");
            }
            out.println("</center>");
            request.getRequestDispatcher("/footer.jsp").include(request, response);
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
