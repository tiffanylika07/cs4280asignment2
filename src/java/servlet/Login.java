/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabean.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ktkeung2
 */
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Book Store - Login</title>");
            out.println("</head>");
            out.println("<body>");
            UserDB userDB = new UserDB();
            if (!userDB.isValidUser(request.getParameter("username"), request.getParameter("password"))) {
                out.println("<h1>Wrong username or password. Please try again.</h1>");
                out.println("<a href=\"javascript:window.history.back();\">Back</a>");
            } else {
                User user = UserDB.getUser(htmlEncode(request.getParameter("username")));
                HttpSession session = request.getSession();
                session.setMaxInactiveInterval(900*1000);
                session.setAttribute("userInfo", user);
                out.println("<h1>You Login as " + htmlEncode(request.getParameter("username")) + " successfully</h1>");
                out.println("<h1>You will be back to menu in 3 seconds.</h1>");
                out.println("<meta http-equiv=\"Refresh\" content=\"3;url=./index.jsp\">");
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    protected void processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Book Store - Logout</title>");
            out.println("</head>");
            out.println("<body>");
            HttpSession session = request.getSession();
            if (session.getAttribute("userInfo") == null) {
                out.println("<h1>You are already logged out.</h1>");
                out.println("<a href=\"javascript:window.history.back();\">Back</a>");
            } else {
                session.setAttribute("userInfo", null);
                out.println("<h1>You are successfully logged out.</h1>");
                out.println("<h1>You will be back to menu in 3 seconds.</h1>");
                out.println("<meta http-equiv=\"Refresh\" content=\"3;url=./index.jsp\">");
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        if ("logout".equals(request.getParameter("action"))){
            processLogout(request, response);
        }else{
            processRequest(request, response);
        }
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

    private String htmlEncode(String s) {

        StringBuffer sb = new StringBuffer(s.length() * 2);

        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if ((ch >= '?' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch == ' ') || (ch == '\n')) {
                sb.append(ch);
            } else {
                switch (ch) {
                    case '>':
                        sb.append("&gt;");
                        break;
                    case '<':
                        sb.append("&lt;");
                        break;
                    case '&':
                        sb.append("&amp;");
                        break;
                    case '\'':
                        sb.append("&#039;");
                        break;
                    case '"':
                        sb.append("&quot;");
                        break;
                    default:
                        sb.append("&#");
                        sb.append(new Integer(ch).toString());
                        sb.append(';');
                }
            }
        }

        return sb.toString();
    }
}
