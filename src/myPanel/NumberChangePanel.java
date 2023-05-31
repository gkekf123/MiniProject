package myPanel;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.*;
import javax.swing.event.*;

import dao.MyHomeDAO;
import db_info.DBProperties;
import frame.LoginRegisterFrame;
import vo.MyHomeVO;

// 번호 변경 패널
public class NumberChangePanel extends JPanel{
	private MyHomeVO vo = new MyHomeDAO().getHome(LoginRegisterFrame.getLoginUser().getACCOUNT_ID());
	private CardLayout layout;
	private JPanel card;
	private InfoPannel info;
	
	private String url = DBProperties.URL;
	private String uid = DBProperties.UID;
	private String upw = DBProperties.UPW;
	private String accountId;
	private String phone_num;
	
	private JLabel main;
	private JLabel first;
	private JLabel second;
	private JButton backBtn = new JButton("<");
	private JButton change;
	
	private JTextField txtNumber;
	
	public NumberChangePanel(JPanel card, InfoPannel info) {
		this.card = card;
		layout = (CardLayout) card.getLayout();
		this.info = info;
		
		setLayout(null);
		setBackground(Color.white);
		setSize(400, 600);
		// 패널 제목
		main = new JLabel("휴대폰 번호 변경하기");
		main.setHorizontalAlignment(JLabel.LEFT);
		main.setBounds(70, 20, 300, 40);
		add(main);
		// 첫번째 라벨
		first = new JLabel("새로운 휴대폰 번호를 입력하세요");
		first.setHorizontalAlignment(JLabel.LEFT);
		first.setBounds(20, 80, 200, 35);
		add(first);
		// 안내 라벨
		second = new JLabel("- 을 제외하고 입력해주세요");
		second.setHorizontalAlignment(JLabel.LEFT);
		second.setBounds(20, 130, 300, 20);
		add(second);
		// 번호를 입력받을 텍스트 필드
		txtNumber = new JTextField(11);
		txtNumber.setDocument(new LimitDocument(11)); // 입력 글자 제한
		txtNumber.setBounds(20, 160, 352, 35);
		txtNumber.setText("휴대폰 번호");
		// 텍스트 필드가 비어있으면 '휴대폰 번호'를 띄우고, 입력 시 사라지게 설정
		txtNumber.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtNumber.getText().isEmpty()) {
					txtNumber.setText("휴대폰 번호");
				}
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(txtNumber.getText().equals("휴대폰 번호")) {
					txtNumber.setText("");
				}
			}
		});
		//  텍스트필드의 상태에 따라 버튼 활성화 여부를 바꾼다
		txtNumber.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkText();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkText();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				checkText();
			}
		});
		
		add(txtNumber);
		
		// 번호 변경 버튼
		change = new JButton("휴대폰 번호 변경하기");
		change.setBounds(0, 501, 384, 60);
		change.setEnabled(false);
		change.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Connection conn = null;
				PreparedStatement pstmt = null;
				
				String sql = "UPDATE accounts SET phone_number = ?\r\n"
						   + "WHERE account_id = ?";
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");

					conn = DriverManager.getConnection(url, uid, upw);
					
					pstmt = conn.prepareStatement(sql);
					
					//입력받은 숫자를 3개, 4개, 4개로 나누고, 사이에 -를 삽입하여 phone_num을 만든다
					StringBuilder sb = new StringBuilder(txtNumber.getText());
					sb.insert(3, "-");
					sb.insert(8, "-");
					phone_num = sb.toString();
					
					pstmt.setString(1, phone_num);
					accountId = vo.getAccount_id();
					pstmt.setString(2, accountId);
					
					pstmt.executeUpdate();
					txtNumber.setText("휴대폰 번호");
					info.updatePanel();
					layout.show(card, "Info");
					
				} catch (Exception e2) {
					e2.printStackTrace();
				} finally {
					try {
						conn.close();
						pstmt.close();
					} catch (Exception e3) {
						e3.printStackTrace();
					}
				}
				
				
			}
		});
		add(change);
		
		
		backBtn.setBounds(10, 20, 50, 50);
		setBtn(backBtn);
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtNumber.setText("휴대폰 번호");
				layout.show(card, "Info");
			}
		});
		
		add(backBtn);

		setVisible(false);
	}
	// 버튼 형태
	private void setBtn(JButton btn) {
		btn.setHorizontalAlignment(JButton.LEFT);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false); 
		btn.setFocusPainted(false);
	}
	// 버튼 활성화 여부 메서드
	private void checkText() {
		String txt = txtNumber.getText();
		if(txt.equals("휴대폰 번호") || txt.isEmpty()) {
			change.setEnabled(false);
		} else {
			change.setEnabled(true);
		}	
	}
	
}
