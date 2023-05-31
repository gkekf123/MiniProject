package myPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dao.MyHomeDAO;
import frame.LoginRegisterFrame;
import mypage.*;
import vo.MyHomeVO;

public class HomePanel extends JPanel {
	private MyHomeVO vo = new MyHomeDAO().getHome(LoginRegisterFrame.getLoginUser().getACCOUNT_ID());
	private CardLayout layout;
	private JPanel card;
	
	private JLabel Name;
	private JLabel E_Mail;
	private JLabel P_Number;
	private JLabel label;
	
	private JButton btnLike;
	private JButton btnSell;
	private JButton btnBuy;
	private JButton btnInfo;
	
	private JButton LikeBtn = new JButton("❤️ 찜 목록");
	private JButton SellBtn = new JButton("📃 판매 내역");
	private JButton BuyBtn = new JButton("👜 구매 내역");
	private JButton InfoBtn = new JButton("⚙️");
	
	private Font f1,f2,f3,f4,f5;
	
	public HomePanel(JPanel card) {
		this.card = card;
		layout = (CardLayout) card.getLayout();
		
		f1 = new Font("SanSerif", Font.BOLD,25);
		f2 = new Font("Dialog", Font.BOLD, 17);
		f3 = new Font("Dialog", Font.BOLD, 15);
		f4 = new Font("SanSerif", Font.BOLD, 25);
		f5 = new Font("Malgun Gothic", Font.PLAIN, 15);
		// 레이아웃 설정
		setBackground(Color.white);
		setSize(400, 600);
		setLayout(null);
		// 각 요소들을 vo의 getter메서드를 사용해 받아온다
		Name = new JLabel(vo.getName());
		E_Mail = new JLabel("e mail: " + vo.getE_mail());
		P_Number = new JLabel("전화번호: " + vo.getPhone_number());
		label = new JLabel("나의 거래");
		// 닉네임 폰트 설정
		Name.setFont(f1);
		Name.setHorizontalAlignment(JLabel.LEFT);
		// 라벨 폰트 설정
		label.setFont(f2);
		label.setHorizontalAlignment(JLabel.LEFT);
		E_Mail.setFont(f5);
		P_Number.setFont(f5);
		
		// 각 요소 배치
		Name.setBounds(40, 50, 300, 30);
		E_Mail.setBounds(40, 90, 500, 30);
		P_Number.setBounds(40, 120, 500, 30);
		label.setBounds(20, 180, 300, 20);
		// 각 요소 추가
		add(Name);
		add(label);
		add(E_Mail);
		add(P_Number);
		// 찜 목록으로 이동
		btnLike = LikeBtn;
		setBtn(btnLike);
		btnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LikeList likeList = new LikeList();
				likeList.setVisible(true);
				setVisible(false);
			}
		});
		btnLike.setBounds(30, 250, 150, 30);
		add(btnLike);
		// 판매 목록으로 이동
		btnSell = SellBtn;
		setBtn(btnSell);
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellList sellList = new SellList();
				sellList.setVisible(true);
			}
		});
		btnSell.setBounds(30, 320, 130, 30);
		add(btnSell);
		// 구매 목록으로 이동
		btnBuy = BuyBtn;
		setBtn(btnBuy);
		btnBuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BuyList buyList = new BuyList();
				buyList.setVisible(true);
			}
		});
		btnBuy.setBounds(30, 390, 130, 30);
		add(btnBuy);
		// 정보 수정 으로 이동
		btnInfo = InfoBtn;
		setBtn(btnInfo);
		btnInfo.setFont(f4);
		btnInfo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(card, "Info");
			}
		});
		btnInfo.setBounds(322, 10, 80, 80);
		add(btnInfo);
		// 마이페이지로 넘어가면 첫 화면으로 보이게 설정
		setVisible(true);
		
	}
	// 버튼 설정 메서드
	private void setBtn(JButton btn) {
		btn.setFont(f3);
		btn.setHorizontalAlignment(JButton.LEFT);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false); 
		btn.setFocusPainted(false);
	}
	// 패널 이동시 각 요소 새로고침을 위한 메서드
	public void updatePanel() {
		vo = new MyHomeDAO().getHome(LoginRegisterFrame.getLoginUser().getACCOUNT_ID()); 
		E_Mail.setText(vo.getE_mail());
		P_Number.setText(vo.getPhone_number());
	}
}
