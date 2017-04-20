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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javabean.Book;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tiffanyli
 */
public class AdminController extends HttpServlet {

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
            
            if(action.equals("main")){
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/adminMenu.jsp");
                dispatcher.forward(request, response);
            }
            else if (action.equals("manageBooks")){
                this.manageBookList(request,response);
            }
            else if(action.equals("getBook")){
                this.getBook(request,response);
            }
            else if (action.equals("editBook")){
                this.editBook(request, response);
            }
            else if (action.equals("removeBook")){
                this.removeBook(request,response);
            }
            else if (action.equals("addBook")){
                this.addBook(request, response);
            }
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

    private void manageBookList(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        PrintWriter out = response.getWriter();
        try{
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM [Book]";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
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
                    book.setCategory_ID(rs.getInt("Category_ID"));
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

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/manageBookList.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex);
        }
    }

    private void getBook(HttpServletRequest request, HttpServletResponse response) 
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
                    aBook.setCategory_ID(rs.getInt("Category_ID"));
                    aBook.setDescription(rs.getString("Description"));
                    aBook.setLoyalty_Point(rs.getInt("Loyalty_Point"));
                    aBook.setImg_File_Name(rs.getString("Img_File_Name"));
            }
            // Store info in request attribute, before forward to views
            request.setAttribute("requestedBook", aBook);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editBook.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex);
        }
    }
    
    private void editBook(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        PrintWriter out = response.getWriter();
        try{
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "UPDATE [BOOK] "
                    + "SET [Book_Name] = ?, "
                    + "    Author = ?, "
                    + "    Price = ?, "
                    + "    Press = ?, "
                    + "    Category_ID = ?, "
                    + "    Description = ?, "
                    + "    Loyalty_Point = ? "
                    + "WHERE [ID] = ?";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1,request.getParameter("bookName"));
            pStmnt.setString(2,request.getParameter("Author"));
            pStmnt.setInt(3,Integer.parseInt(request.getParameter("Price")));
            pStmnt.setString(4,request.getParameter("Press"));
            pStmnt.setInt(5,Integer.parseInt(request.getParameter("Category")));
            pStmnt.setString(6,request.getParameter("Description"));
            pStmnt.setInt(7,Integer.parseInt(request.getParameter("Loyalty_Points")));
            pStmnt.setInt(8,Integer.parseInt(request.getParameter("bookID")));
                    
            // execute the SQL statement
                int rows = pStmnt.executeUpdate();
                this.getBook(request,response);

        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            out.println(ex);
        }
    }

    private void removeBook(HttpServletRequest request, HttpServletResponse response) 
              throws ServletException, IOException{
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        PrintWriter out = response.getWriter();
        try{
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "DELETE from [BOOK] "
                    + "WHERE [ID] = ?";

            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1,Integer.parseInt(request.getParameter("bookID")));
                    
            // execute the SQL statement
             int rows = pStmnt.executeUpdate();
            out.println(" <script type=\"text/javascript\">");
            out.println("confirm('Deleted Successfully');");
            out.println("document.location.href=\"./AdminController?action=manageBooks\";");
            out.println("</script>");

        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            out.println(ex);
        }
    }
    
    private void addBook(HttpServletRequest request, HttpServletResponse response) 
              throws ServletException, IOException{
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        PrintWriter out = response.getWriter();
        try{
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement =  "INSERT INTO [BOOK] "
                            + "(   [Book_Name],"
                            + "    [Author]," 
                            + "    [Price]," 
                            + "    [Press]," 
                            + "    [Category_ID]," 
                            + "    [Description], "
                            + "    [Loyalty_Point]) "
                            +    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1,request.getParameter("bookName"));
            pStmnt.setString(2,request.getParameter("Author"));
            pStmnt.setInt(3,Integer.parseInt(request.getParameter("Price")));
            pStmnt.setString(4,request.getParameter("Press"));
            pStmnt.setInt(5,Integer.parseInt(request.getParameter("Category")));
            pStmnt.setString(6,request.getParameter("Description"));
            pStmnt.setInt(7,Integer.parseInt(request.getParameter("Loyalty_Points")));
                    
                    
            // execute the SQL statement
             int rows = pStmnt.executeUpdate();
            out.println(" <script type=\"text/javascript\">");
            out.println("confirm('Added Successfully');");
            out.println("window.opener.location.href=\"./AdminController?action=manageBooks\";");
            out.println("window.close()");
            out.println("</script>");

        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            out.println(ex);
        }
    }

}
