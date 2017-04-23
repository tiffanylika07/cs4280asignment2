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

public class RefundDB {

    public static boolean addRecord(int SalesID) {
        Connection connection = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            connection = ConnectionUtil.getConnection();
            pStmnt = connection.prepareStatement("INSERT INTO [RefundReq] (Date,SalesID,Approve) VALUES (CURRENT_TIMESTAMP , ? , 'N')");
            pStmnt.setInt(1, SalesID);
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
            Logger.getLogger(SalesDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isSuccess;
    }

    public static boolean updateStatus(int refundID, String status, String managerName) {
        Connection connection = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            connection = ConnectionUtil.getConnection();
            pStmnt = connection.prepareStatement("UPDATE [RefundReq] SET Approve = ?, ApproveBy = ? WHERE  ID = ?");
            pStmnt.setString(1, status);
            pStmnt.setString(2, managerName);
            pStmnt.setInt(3, refundID);
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
            Logger.getLogger(SalesDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isSuccess;
    }
    
        public static int getSalesID(int refundID) {
        Connection connection = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;

        try {
            connection = ConnectionUtil.getConnection();
            pStmnt = connection.prepareStatement("SELECT SalesID FROM [RefundReq] WHERE  ID = ?");
            pStmnt.setInt(1, refundID);
            ResultSet rs = pStmnt.executeQuery();
            if (rs.next()) {
                return rs.getInt("SalesID");
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SalesDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
}
