package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vo.MainVO;

public class MainDAO {
    private String DB_URL;
    private String DB_USERNAME;
    private String DB_PASSWORD;

    public MainDAO(String dbUrl, String dbUsername, String dbPassword) {
        this.DB_URL = dbUrl;
        this.DB_USERNAME = dbUsername;
        this.DB_PASSWORD = dbPassword;
    }

    public List<MainVO> getAllBoards() {
        List<MainVO> boards = new ArrayList<>();

        String query = "SELECT * FROM BOARDS";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String boardNum = rs.getString("BOARD_NUM");
                String accountId = rs.getString("ACCOUNT_ID");
                String productName = rs.getString("PRODUCT_NAME");
                String productContent = rs.getString("PRODUCT_CONTENT");
                String price = rs.getString("PRICE");
                Date boardDate = rs.getDate("BOARD_DATE");
                String productSell = rs.getString("PRODUCT_SELL");

                MainVO board = new MainVO(boardNum, accountId, productName, productContent, price, boardDate, productSell);
                boards.add(board);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return boards;
    }
}
