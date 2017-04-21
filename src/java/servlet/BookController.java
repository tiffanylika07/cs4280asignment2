/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.ConnectionUtil;
import database.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabean.Book;
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
public class BookController extends HttpServlet {

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
        
        try {
            String action = request.getParameter("action");
            String category = request.getParameter("categoryID");
            
            if("category".equals(action) && category != null){
                this.viewBooksByCategory(request, response);
            }
            else if ("single".equals(action)){
                this.viewSingleBook(request, response);
            }
            else if (action.equals("search")){
                    this.searchBook(request,response);
            }
            else if (action.equals("viewSearch")){
                response.sendRedirect("./searchBooks.jsp");
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void viewBooksByCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        PrintWriter out = response.getWriter();
        try{
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM [Book] where Category_ID = ?";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, Integer.parseInt(request.getParameter("categoryID")));
            ResultSet rs = pStmnt.executeQuery();
            List<Book> list = new ArrayList<Book>();
            while(rs.next()){ 
                    Book book = new Book();
                    book.setBook_Name(rs.getString("Book_Name"));
                    book.setID(rs.getInt("ID"));
                    book.setAuthor(rs.getString("Author"));
                    book.setPrice(rs.getInt("Price"));
                    book.setPress(rs.getString("Press"));
                    book.setDescription(rs.getString("Description"));
                    book.setLoyalty_Point(rs.getInt("Loyalty_Point"));
                    book.setImg_File_Name(rs.getString("Img_File_Name"));
                    list.add(book);
            }
            //Check if there are no matching records
            if(list.size()==0){
                request.setAttribute("noRecords",true);
            }
            else{
                request.setAttribute("noRecords",true);
            }
            String pStmt2 = "SELECT * FROM [Category] where Category_ID = ?";
            PreparedStatement pstmt2 = null;
            pstmt2 = cnnct.prepareStatement(pStmt2);
            pstmt2.setInt(1, Integer.parseInt(request.getParameter("categoryID")));
            ResultSet rs2 = pstmt2.executeQuery();
            String categoryName ="";
            if(rs2.next()){
                categoryName = rs2.getString("Category_Name");
            }
            
            // Store info in request attribute, before forward to views
            request.setAttribute("bookList", list);
            request.setAttribute("categoryName",categoryName);
            request.setAttribute("action","category");


            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewBooksList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex);
        }
    }
    
    private void viewSingleBook(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        PrintWriter out = response.getWriter();
        try{
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM [Book] where ID = ?";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, Integer.parseInt(request.getParameter("bookID")));
            ResultSet rs = pStmnt.executeQuery();
            Book aBook = new Book();
            if(rs.next()){ 
                    aBook.setBook_Name(rs.getString("Book_Name"));
                    aBook.setID(rs.getInt("ID"));
                    aBook.setAuthor(rs.getString("Author"));
                    aBook.setPrice(rs.getInt("Price"));
                    aBook.setPress(rs.getString("Press"));
                    aBook.setDescription(rs.getString("Description"));
                    aBook.setLoyalty_Point(rs.getInt("Loyalty_Point"));
                    aBook.setImg_File_Name(rs.getString("Img_File_Name"));
            }
            // Store info in request attribute, before forward to views
            request.setAttribute("requestedBook", aBook);
                  // Forward to /WEB-INF/views/productListView.jsp
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewSingleBook.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex);
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

    private void searchBook(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        PrintWriter out = response.getWriter();
        try{
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM [Book] "
                    + "where (Book_Name like ? )or "
                    + "(Author like ? )or "
                    + "(Press like ? )or "
                    + "(Description like ? )";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1,"%"+ request.getParameter("search") +"%");
            pStmnt.setString(2,"%"+ request.getParameter("search") +"%");
            pStmnt.setString(3,"%"+ request.getParameter("search") +"%");
            pStmnt.setString(4,"%"+ request.getParameter("search") +"%");
            ResultSet rs = pStmnt.executeQuery();
            List<Book> list = new ArrayList<Book>();
            while(rs.next()){ 
                    Book book = new Book();
                    book.setBook_Name(rs.getString("Book_Name"));
                    book.setID(rs.getInt("ID"));
                    book.setAuthor(rs.getString("Author"));
                    book.setPrice(rs.getInt("Price"));
                    book.setPress(rs.getString("Press"));
                    book.setDescription(rs.getString("Description"));
                    book.setLoyalty_Point(rs.getInt("Loyalty_Point"));
                    book.setImg_File_Name(rs.getString("Img_File_Name"));
                    list.add(book);
            }
            //Check if there are no matching records
            if(list.size()==0){
                request.setAttribute("noRecords",true);
            }
            else{
                request.setAttribute("noRecords",true);
            }
            // Store info in request attribute, before forward to views
            request.setAttribute("bookList", list);
            request.setAttribute("action", "search");
            request.setAttribute("keywords", request.getParameter("search"));
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewBooksList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex);
        }
    }

}
