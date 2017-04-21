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
import java.util.logging.Level;
import java.util.logging.Logger;
import javabean.Book;
import javabean.CartObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tiffanyli
 */
public class CartController extends HttpServlet {

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
            String quantity = request.getParameter("quantity");
            
            CartObject obj = new CartObject(aBook,Integer.parseInt(quantity));
            HttpSession session = request.getSession();
            ArrayList<CartObject> cartList = (ArrayList<CartObject>)session.getAttribute("cart");
            if(cartList != null)
            {
                cartList.add(obj);
            }
            else{
                cartList = new ArrayList<CartObject>();
                cartList.add(obj);
            }
            
            session.setAttribute("cart", cartList);
            
            out.println("<script type=\"text/javascript\">");
            out.println("confirm('Added Successfully');");
            out.println("document.location.href=\""+request.getHeader("referer")+"\"");
            out.println("</script>");

           
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            out.println(ex.getStackTrace());
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
