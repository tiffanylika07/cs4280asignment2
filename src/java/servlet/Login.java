/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

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
            java.lang.String url = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad028_db;";
            java.lang.Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(url, "aiad028", "aiad028");
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM [USER] WHERE username='" + request.getParameter("username") + "' and password='" + request.getParameter("password") + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                out.println("<h1>Wrong username or password. Please try again.</h1>");
                out.println("<a href=\"javascript:window.history.back();\">Back</a>");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", htmlEncode(resultSet.getString("username")));
                out.println("<h1>You Login as " + htmlEncode(resultSet.getString("username")) + "</h1>");
                out.println("<h1>You will be back to menu in 3 seconds.</h1>");
                out.println("<meta http-equiv=\"Refresh\" content=\"3;url=./index.jsp\">");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            out.println("<div style='color: red'>" + ex.toString() + "</div>");
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            out.println("<div style='color: red'>" + ex.toString() + "</div>");
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
