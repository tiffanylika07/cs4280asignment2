package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import database.ConnectToDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author yuenyauli2
 */
public class Books extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");
        
        ArrayList<String> allCategory = this.getAllCategory();
        request.setAttribute("allCategory", allCategory);
    }

    private void doRetrieveEntry(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            // make connection to db and retrieve data from the table

            try {
                java.lang.Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                out.println("TEST");
                Connection con = DriverManager.getConnection("jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433; databaseName=aiad028_db", "aiad028", "aiad028");
                String sql = ("SELECT * FROM [BOOK] WHERE [Price] >=100");
                PreparedStatement pstmt = con.prepareStatement(sql);
                //Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //ResultSet rs = stmt.executeQuery("SELECT * FROM [book] ORDER BY [Book_Name] ASC");
                ResultSet rs = pstmt.executeQuery();
                // list of data 
                while (rs != null && rs.next() != false) {
                    String bookName = rs.getString("Book_Name");
                    out.println("<tr>");
                    out.println("<td>" + this.htmlEncode(bookName) + "</td>");
                    out.println("<td>");
                    out.println("</td>");
                    out.println("</tr>");
                }

                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (ClassNotFoundException e) {
                out.println("<div style='color: red'>" + e.toString() + "</div>");
                out.println("<h2>" + e.getClass().getSimpleName() + "_" + e.getMessage() + "</h2>");
            } catch (SQLException e) {
                out.println("<div style='color: red'>" + e.toString() + "</div>");
            }
        } finally {
            out.close();
        }
    }

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

    public  ArrayList<String> getAllCategory() {
        Connection con = null;
        PreparedStatement pStmnt = null;
        ArrayList<String> listOfString=new ArrayList<String>();

        try {
            con = ConnectToDB.getConnection();
            String sql = ("SELECT * FROM [BOOK] WHERE [Price] >=100");
                PreparedStatement pstmt = con.prepareStatement(sql);
                //Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //ResultSet rs = stmt.executeQuery("SELECT * FROM [book] ORDER BY [Book_Name] ASC");
                ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                
                listOfString.add(rs.getString("Category_Name"));
                
            }
            
           
            return listOfString;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return listOfString;
        }
    }
    
    
}
