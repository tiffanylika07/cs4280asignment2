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

public class SalesDB {

    public boolean addRecord(String username, int BookID, int quantity, boolean refundable) {
        Connection connection = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        String _refund = "N";
        
        try {
            connection = ConnectionUtil.getConnection();
            pStmnt = connection.prepareStatement("INSERT INTO [SalesLog] (Date,Username,Book_Id,Quantity,Refundable) VALUES (CURRENT_TIMESTAMP , ? , ? , ? ,?)");
            pStmnt.setString(1, username);
            pStmnt.setInt(2, BookID);
            pStmnt.setInt(3, quantity);
            if (refundable){
                _refund = "Y";
            }
            pStmnt.setString(4, _refund);
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
}
