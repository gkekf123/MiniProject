package myPanel;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.MyHomeDAO;
import db_info.DBProperties;
import frame.LoginRegisterFrame;
import vo.MyHomeVO;

public class MailChangePanel extends JPanel {
	private MyHomeVO vo = new MyHomeDAO().getHome(LoginRegisterFrame.getLoginUser().getACCOUNT_ID());
	private JPanel card;
	private CardLayout layout;
	private InfoPannel info;
	
	private static String url = DBProperties.URL;
	private static String uid = DBProperties.UID;
	private static String upw = DBProperties.UPW;
	private String accountId;

	private JButton backBtn = new JButton("<");
	private JButton change;
	private JLabel main;
	private JLabel first;
	private JLabel second;

	private JComboBox<String> Box;
	private JTextField txtMail;
	private String mail;

	public MailChangePanel(JPanel card, InfoPannel info) {
		this.card = card;
		layout = (CardLayout) card.getLayout();
		this.info = info;
		
		setLayout(null);
		setBackground(Color.white);
		setSize(400, 600);
		// 패널 제목
		main = new JLabel("이메일 변경하기");
		main.setHorizontalAlignment(JLabel.LEFT);
		main.setBounds(70, 20, 300, 40);
		add(main);

		// 패널 정보
		first = new JLabel("이메일 주소를 입력해주세요");
		first.setHorizontalTextPosition(JLabel.LEFT);
		first.setBounds(40, 80, 300, 50);
		add(first);

		// 텍스트 필드를 설정
		txtMail = new JTextField(20);
		txtMail.setBounds(20, 150, 160, 40);
		txtMail.setText("이메일 주소");

		// 입력 전에는 '이메일 주소'가 떠있고, 입력 하기위해 클릭시, '이메일 주소'가 사라짐
		txtMail.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtMail.getText().isEmpty())
					txtMail.setText("이메일 주소");
			}
			@Override
			public void focusGained(FocusEvent e) {
				if(txtMail.getText().equals("이메일 주소"))
					txtMail.setText("");
			}
		});

		// 입력 시, 버튼을 활성화
		txtMail.getDocument().addDocumentListener(new DocumentListener() {

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

		// 드롭박스 버튼을 설정
		String[] options = {"...선택", "@gmail.com", "@naver.com", "@daum.net"};
		Box = new JComboBox<>(options);
		Box.setBounds(200, 150, 160, 40);
		Box.addActionListener(new ActionListener() {
			// 드롭박스에서 선택시 텍스트 필드의 문자열과 드롭박스 선택 문자열을 합침
			public void actionPerformed(ActionEvent e) {
				String select = (String) Box.getSelectedItem();
				String current = txtMail.getText();
				mail = current + select;
			}
		});

		add(Box);

		add(txtMail);

		//이메일이 이미 사용중일때 알려줄 문장
		second = new JLabel("이메일이 이미 사용중입니다");
		second.setHorizontalTextPosition(JLabel.LEFT);
		second.setBounds(40, 200, 200, 30);
		second.setVisible(false);
		add(second);

		// 이메일 변경 버튼
		change = new JButton("이메일 변경하기");
		change.setBounds(0, 501, 384, 60);
		change.setEnabled(false); // 기본적으로 비활성화 상태로 설정
		change.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Connection conn = null;
				PreparedStatement pstmt = null;

				String sql = "UPDATE accounts SET email = ?\r\n"
						   + "WHERE account_id = ?";

				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");

					conn = DriverManager.getConnection(url, uid, upw);

					pstmt = conn.prepareStatement(sql);

					// 입력받은 mail을 sql문에
					pstmt.setString(1, mail);
					// 입력받은 mail이 같다면 -> 예외 발생하며 문자열 나타나게
					if(!mail.equals(vo.getE_mail())) {
						accountId = vo.getAccount_id();
						pstmt.setString(2, accountId);
						pstmt.executeUpdate();
						// 메일이 변경되면, 정보 수정 패널로 이동
						txtMail.setText("이메일 주소");
						second.setVisible(false);
						info.updatePanel();
						layout.show(card, "Info");
					} else {
						throw new Exception("이메일이 이미 사용중입니다");
					}

				} catch (Exception e1) {
					second.setVisible(true);
				} finally {
					try {
						conn.close();
						pstmt.close();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		add(change);

		// 뒤로 가기 버튼
		backBtn.setBounds(10, 20, 50, 50);
		setBtn(backBtn);
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtMail.setText("이메일 주소");
				layout.show(card, "Info");
			}
		});

		add(backBtn);

		setVisible(false);

	}
	// 버튼 형식 메서드
	private void setBtn(JButton btn) {
		btn.setHorizontalAlignment(JButton.LEFT);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false); 
		btn.setFocusPainted(false);
	}

	// 텍스트필드에 값이 입력되었는지를 판단하는 메서드
	private void checkText() {
		String txt = txtMail.getText();
		// 텍스트 필드가 "이메일 주소" 또는 비어있다면 버튼 비활성화
		if(txt.equals("이메일 주소") || txt.isEmpty() ) {
			change.setEnabled(false);
		} else {
			change.setEnabled(true);
		}	
	}
	
}
