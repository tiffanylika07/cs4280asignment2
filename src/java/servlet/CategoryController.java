/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.ConnectToDB;
import database.ConnectionUtil;
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
import javabean.Category;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tiffanyli
 */
public class CategoryController extends HttpServlet {

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
            Connection conn;
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        PrintWriter out = response.getWriter();
        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM [Category] order by Category_Name";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = pStmnt.executeQuery();
            List<Category> list = new ArrayList<Category>();
            while(rs.next()){ 
                    Category category=new Category();
                    category.setCategoryName(rs.getString("Category_Name"));
                    category.setID(rs.getInt("Category_ID"));
                    category.setImgSrc(rs.getString("Img_Src_Name"));
                    list.add(category);
            }
        // Store info in request attribute, before forward to views
        request.setAttribute("categoryList", list);
        
     
        // Forward to /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewCategory.jsp");
        dispatcher.forward(request, response);
        
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
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
