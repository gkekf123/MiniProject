package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db_info.DBProperties;
import frame.LoginRegisterFrame;
import vo.BoardVO;

public class BoardDAO {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public BoardDAO() {
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
	
	//글 작성
	public int writeBoard(BoardVO vo) {
		int result = 0;
		String boardWriteSql = "INSERT INTO BOARDS VALUES(boards_seq.nextval, ?, ?, ?, ?, SYSDATE, ?)";
		try {
			pstmt = conn.prepareStatement(boardWriteSql);
			pstmt.setString(1, LoginRegisterFrame.getLoginUser().getACCOUNT_ID());
			pstmt.setString(2, vo.getProduct_name());
			pstmt.setString(3, vo.getProduct_content());
			pstmt.setString(4, vo.getPrice());
			pstmt.setString(5, vo.getProduct_sell());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}