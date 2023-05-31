package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db_info.DBProperties;
import frame.LoginRegisterFrame;
import vo.BoardListVO;

import java.util.ArrayList;

public class LikeDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public LikeDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("CLASS FOR NAME ERR");
		}
	}

	//oracle 연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(DBProperties.URL, DBProperties.UID, DBProperties.UPW);
		} catch (Exception e) {
			System.out.println("CONNECTION ERR");
		}
	}

	//oracle 연결 해제
	public void disConnection() {
		try {
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (Exception e) { }
	}

	public List<BoardListVO> showLikeList() {
		List<BoardListVO> boardList = new ArrayList<>();
		String showLikeSql = "SELECT * FROM BOARDS B JOIN ACCOUNTS A ON B.ACCOUNT_ID = A.ACCOUNT_ID WHERE BOARD_NUM IN (SELECT BOARD_NUM FROM LIKES WHERE ACCOUNT_ID = ?)";

		try {
			pstmt = conn.prepareStatement(showLikeSql);
			pstmt.setString(1, LoginRegisterFrame.getLoginUser().getACCOUNT_ID());
			rs = pstmt.executeQuery();

			while(rs.next()) {
				BoardListVO board = new BoardListVO();
				board.setProduct_name(rs.getString("PRODUCT_NAME"));
				board.setName(rs.getString("NAME"));
				board.setPrice(rs.getString("PRICE"));
				board.setProduct_sell(rs.getString("PRODUCT_SELL"));
				board.setBoard_date(rs.getDate("BOARD_DATE"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return boardList;
	}
}