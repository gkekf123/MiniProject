package frame;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import dao.MyHomeDAO;
import vo.MyHomeVO;
import myPanel.*;
import mypage.LikeList;

public class MyFrame extends JFrame {
	
	public MyHomeVO vo = new MyHomeDAO().getHome(LoginRegisterFrame.getLoginUser().getACCOUNT_ID());
	private CardLayout cardLayout;
	private JPanel card;
	
	
	public MyFrame() {
		setSize(400, 600);
		setLocationRelativeTo(null); // 화면 가운데에서 뜨도록 설정
		setResizable(false); // 사이즈 조절 불가
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// 창을 닫을 시 현재의 프레임만 닫기
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				
				JFrame currentFrame = (JFrame) e.getWindow();
				currentFrame.dispose();
			}
			
		});
		// CardLayout 생성
		cardLayout = new CardLayout();
		// 패널을 담을 카드 패널 생성
		card = new JPanel(cardLayout);
		
		// 각 패널 인스턴스 생성
		HomePanel home = new HomePanel(card);
		InfoPannel info = new InfoPannel(card, home);
		MailChangePanel mail = new MailChangePanel(card, info);
		NumberChangePanel number = new NumberChangePanel(card, info);
		
		//카드 패널에 패널들 추가
		card.add(home, "MyMain");
		card.add(info, "Info");
		card.add(mail, "Mail");
		card.add(number, "Number");
		// 프레임의 ContentPane에 카드 패널 추가
		getContentPane().add(card);
	}
}
