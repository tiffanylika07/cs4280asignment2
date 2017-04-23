package database;

import javabean.User;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDB {

    public static String getBookName(int bookID) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT Book_Name FROM [Book] WHERE ID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, bookID);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                return rs.getString("Book_Name");
            } else {
                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static int getPrice(int bookID) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT Price FROM [Book] WHERE ID=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setInt(1, bookID);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Price");
            } else {
                return 0;
            }
        } catch (Exception ex) {
            Logger.getLogger(BookDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
