package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db_info.DBProperties;
import mypage.*;
import vo.MyHomeVO;

public class MyHomeDAO { // 원래 AccountDAO : 테스트위해 임시 제작
	
	private String url = DBProperties.URL;
	private String uid = DBProperties.UID;
	private String upw = DBProperties.UPW;
	
	public MyHomeDAO() {
		try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("로그인 실패");
		}
	}
	// id에 맞게 accountvo를 리턴
	public MyHomeVO getHome(String id){ 
		
		MyHomeVO vo = new MyHomeVO();
		
		String sql = "SELECT * FROM accounts WHERE account_id = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, uid, upw);
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				String account_id = rs.getString("account_id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String e_mail = rs.getString("email");
				String phone_number = rs.getString("phone_number");
				String address = rs.getString("ADDRESS");
				
				vo = new MyHomeVO(account_id, pw, name, e_mail, phone_number, address);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("로그인 실패");
		} finally {
			try {
				conn.close();
				psmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
}
