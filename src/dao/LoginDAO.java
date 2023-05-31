package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db_info.DBProperties;
import vo.LoginVO;



public class LoginDAO {

    List<LoginVO> list = new ArrayList<>();
    
    public boolean check(LoginVO loginVO) {
        String query = "SELECT * FROM ACCOUNTS WHERE ACCOUNT_ID = ? AND PW = ?";

        try (Connection conn = DriverManager.getConnection(DBProperties.URL, DBProperties.UID, DBProperties.UPW);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, loginVO.getACCOUNT_ID());
            pstmt.setString(2, loginVO.getPW());

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean register(LoginVO loginVO) {
        String query = "INSERT INTO ACCOUNTS (ACCOUNT_ID, PW, NAME, EMAIL, PHONE_NUMBER, ADDRESS) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DBProperties.URL, DBProperties.UID, DBProperties.UPW);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
           pstmt.setString(1, loginVO.getACCOUNT_ID());
            pstmt.setString(2, loginVO.getPW());
            pstmt.setString(3, loginVO.getNAME());
            pstmt.setString(4, loginVO.getEMAIL());
            pstmt.setString(5, loginVO.getPHONE_NUMBER());
            pstmt.setString(6, loginVO.getADDRESS());
            
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean checkUsernameExists(String username) {
        try (Connection conn = DriverManager.getConnection(DBProperties.URL, DBProperties.UID, DBProperties.UPW);
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM ACCOUNTS WHERE ACCOUNT_ID = ?")) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}