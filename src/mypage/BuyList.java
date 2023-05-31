package mypage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import board.BoardWrite;
import vo.BoardListVO;
import dao.BuyListDAO;
import frame.MyFrame;

import java.io.File;



public class BuyList extends JFrame {
	private JList<BoardListVO> buyList;
	private DefaultListModel<BoardListVO> listModel;
	private JLabel titleLabel;
	private JButton backBtn;

	public BuyList() {
		listModel = new DefaultListModel<>();

		Container c = getContentPane();
		setTitle("구매 내역");
		setSize(400, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		//Frame을 화면 가운데에 정렬
		Dimension frameSize = getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		//Panel - 제목(구매 내역)
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setPreferredSize(new Dimension(WIDTH, 60));
		backBtn = new JButton();

		//현재 프로젝트 경로
		File file = new File(".");
		String rootPath = file.getAbsolutePath();
		ImageIcon icon = new ImageIcon(rootPath + "/images/back.png");

		//이미지 사이즈 줄이기
		Image resizedImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImage);
		backBtn.setIcon(resizedIcon);

		backBtn.setBackground(titlePanel.getBackground()); //버튼색 제거
		backBtn.setBorder(null); // 버튼 안의 이미지 테두리 제거
		backBtn.setBorderPainted(false); // 버튼의 테두리 제거
		
		backBtn.setBounds(10, 20, 50, 50);
		setBtn(backBtn);
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		add(backBtn);
		titleLabel = new JLabel("구매 내역");
		Font titleFont = new Font("Malgun Gothic", Font.BOLD, 16);
		titleLabel.setFont(titleFont);

		titlePanel.add(backBtn, BorderLayout.WEST);
		titlePanel.add(titleLabel, BorderLayout.CENTER);

		//Panel - 구매내역
		JPanel buyListPanel = new JPanel(new BorderLayout());

		buyList = new JList<>(listModel);
		buyList.setCellRenderer(new BoardListRenderer());
		JScrollPane scrollPane = new JScrollPane(buyList);
		buyListPanel.add(scrollPane, BorderLayout.CENTER);

		//데이터 추가
		addBoardData();

		//글 목록에서 글을 더블클릭 했을 때
		buyList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int selectedIndex = buyList.getSelectedIndex();

					if (selectedIndex >= 0) {
						//글 보기 페이지
						BoardListVO selectedBoard = listModel.getElementAt(selectedIndex);
						openPostDetail(selectedBoard);
					}
				}
			}
		});

		c.add(titlePanel, BorderLayout.NORTH);
		c.add(buyListPanel, BorderLayout.CENTER);
		setVisible(false);

	}

	//클릭시 열리는 페이지
	private void openPostDetail(BoardListVO board) {
		//글 보기 페이지
		new BoardWrite();
	}

	private void addBoardData() {
		BuyListDAO buy = new BuyListDAO();

		buy.getConnection();

		for (BoardListVO vo : buy.showBuyList()) {
			listModel.addElement(vo);
		}

		buy.disConnection();
	}

	private static class BoardListRenderer extends DefaultListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof BoardListVO) {
				BoardListVO post = (BoardListVO) value;

				String productName = "<html><span style='font-size: 14pt;'><b>" + post.getProduct_name() + "</b></span>";
				String name = "<font color='gray'>" + post.getName() + "</font>";
				String price = "<html><b>" + post.getPrice() + "원</b></html>";

				setText(productName + "<br>" + name + "<br>" + price);
			}

			setPreferredSize(new Dimension(100, 80));
			setBorder(BorderFactory.createLineBorder(Color.GRAY));
			return this;
		}
	}
	
	private void setBtn(JButton btn) {
		btn.setHorizontalAlignment(JButton.LEFT);
		btn.setBorderPainted(false);
		btn.setContentAreaFilled(false); 
		btn.setFocusPainted(false);
	}
}