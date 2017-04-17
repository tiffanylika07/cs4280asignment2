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

public class UserDB {

    public boolean isValidUser(String username, String password) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM [USER] WHERE username=? AND password=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            pStmnt.setString(2, password);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean isOccupiedUser(String username) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM [USER] WHERE username=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean addRecord(String username, String password, String email, String role) {
        Connection connection = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            connection = ConnectionUtil.getConnection();
            pStmnt = connection.prepareStatement("INSERT INTO [USER] (username,password,email,role) VALUES (? , ? , ? , ?)");
            pStmnt.setString(1, username);
            pStmnt.setString(2, password);
            pStmnt.setString(3, email);
            pStmnt.setString(4, role);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            connection.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isSuccess;
    }
    
        public static User getUser(String username) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;

        try {
            cnnct = ConnectionUtil.getConnection();
            String preQueryStatement = "SELECT * FROM [USER] WHERE username=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, username);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setRole(rs.getString("role"));
                return user;
            } else {
                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
