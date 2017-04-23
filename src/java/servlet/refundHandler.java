/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.BookDB;
import database.ConnectionUtil;
import database.RefundDB;
import database.SalesDB;
import database.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabean.Book;
import javabean.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kamtso
 */
public class refundHandler extends HttpServlet {

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
        String action = request.getParameter("action");
        if (action.equals("request")) {
            refundRequest(request, response);
        } else if (action.equals("reject")) {
            refundReject(request, response);
        } else if (action.equals("accept")) {
            refundAccept(request, response);
        }
    }

    protected void refundRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int SalesID = Integer.parseInt(request.getParameter("SalesID"));
        if (SalesDB.refundStatus(SalesID, "P", "Y")) {
            if (RefundDB.addRecord(SalesID)) {
                out.println("<script type=\"text/javascript\">");
                out.println("confirm('Refund Request Pending!');");
                out.println("document.location.href=\"" + request.getHeader("referer") + "\"");
                out.println("</script>");
            }
        }
    }

    protected void refundReject(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int refundID = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("userInfo");
        String managerName = user.getUsername();
        if (RefundDB.updateStatus(refundID, "R", managerName)) {
            if (SalesDB.refundStatus(RefundDB.getSalesID(refundID), "R", "P")) {
                out.println("<script type=\"text/javascript\">");
                out.println("confirm('Refund Rejected!');");
                out.println("document.location.href=\"" + request.getHeader("referer") + "\"");
                out.println("</script>");
            }
        }
    }

    protected void refundAccept(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int refundID = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("userInfo");
        String managerName = user.getUsername();
        if (RefundDB.updateStatus(refundID, "Y", managerName)) {
            if (SalesDB.refundStatus(RefundDB.getSalesID(refundID), "A", "P")) {
                try {
                    Connection connection = ConnectionUtil.getConnection();
                    PreparedStatement pStmnt;
                    pStmnt = connection.prepareStatement("SELECT Username, Quantity FROM [SalesLog] WHERE ID=?");
                    pStmnt.setInt(1, RefundDB.getSalesID(refundID));
                    ResultSet rs = pStmnt.executeQuery();
                    String username = null;
                    int quantity = 0;
                    if (rs.next()) {
                        username = rs.getString("Username");
                        quantity = rs.getInt("Quantity");
                    }
                    if (UserDB.addMoney(username, BookDB.getPrice(SalesDB.getBookID(RefundDB.getSalesID(refundID)))*quantity)) {
                        out.println("<script type=\"text/javascript\">");
                        out.println("confirm('Refund Accepted!');");
                        out.println("document.location.href=\"" + request.getHeader("referer") + "\"");
                        out.println("</script>");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(refundHandler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(refundHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
