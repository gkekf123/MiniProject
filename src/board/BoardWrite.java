package board;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vo.BoardVO;
import dao.BoardDAO;
import dao.MainDAO;
import db_info.DBProperties;
import frame.LoginRegisterFrame;
import frame.MainFrame;

public class BoardWrite extends JFrame {
	private JLabel name;
	private JTextField productName;
	private JLabel price;
	private JTextField productPrice;
	private JTextArea content;
	private JLabel sell;
	private JComboBox comboBox;
	private JButton btn;
	private BoardVO vo;
	public BoardVO getVo() {
		return vo;
	}

	public void setVo(BoardVO vo) {
		this.vo = vo;
	}

	public BoardWrite() {
		vo = new BoardVO();
		setTitle("Market");
		setSize(400,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Frame을 화면 가운데에 정렬
		Dimension frameSize = getSize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5);

		//컨텐트팬 - 컴포넌트 어디에 부착할지 지정
		Container c = getContentPane();
		c.setLayout(new FlowLayout()); //전체 레이아웃

		//Panel - 제목, 가격
		JPanel namePricePanel = new JPanel();
		namePricePanel.setLayout(new GridBagLayout());
		name = new JLabel("제품명");
		namePricePanel.add(name, gbc);

		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		productName = new JTextField(20);
		namePricePanel.add(productName, gbc);

		productName.getDocument().addDocumentListener(new DocumentListener() {
			
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
		
		price = new JLabel("가격");

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		namePricePanel.add(price, gbc);

		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		productPrice = new JTextField(20);
		namePricePanel.add(productPrice, gbc);

		sell = new JLabel("판매 여부");
		gbc.gridx = 0;
		gbc.gridy++;
		gbc.fill = GridBagConstraints.NONE;
		namePricePanel.add(sell, gbc);

		gbc.gridx++;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"판매중", "판매완료"}));
		namePricePanel.add(comboBox, gbc);

		//Panel - 내용
		JPanel contentPanel = new JPanel();
		content = new JTextArea(15, 30);
		contentPanel.add(content);

		//Panel - 판매 여부, 등록
		JPanel btnPanel = new JPanel();
		btn = new JButton("등록");
		btn.setPreferredSize(new Dimension(200, 40));
		btn.setEnabled(false); // 기본적으로 비활성화 상태로 설정
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(BoardWrite.this, "글이 작성되었습니다.");
				BoardDAO dao = new BoardDAO();

				if (e.getSource() == btn) {
					String product_name = productName.getText();
					vo.setProduct_name(product_name);

					String product_price = productPrice.getText();
					vo.setPrice(product_price);

					String product_sell = comboBox.getSelectedItem().toString();
					vo.setProduct_sell(product_sell);

					String product_content = content.getText();
					vo.setProduct_content(product_content);

					dao.getConnection();
					dao.writeBoard(vo);
					dao.disConnection();
				}
				MainDAO boardDAO = new MainDAO(DBProperties.URL, DBProperties.UID, DBProperties.UPW);
				MainFrame main = new MainFrame(boardDAO);
				main.setVisible(true);
				setVisible(false);
			}
		});
		btnPanel.add(btn);

		c.add(namePricePanel);
		c.add(contentPanel);
		c.add(btnPanel);

		setVisible(false);
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		BoardDAO dao = new BoardDAO();
//
//		if (e.getSource() == btn) {
//			String product_name = productName.getText();
//			vo.setProduct_name(product_name);
//
//			String product_price = productPrice.getText();
//			vo.setPrice(product_price);
//
//			String product_sell = comboBox.getSelectedItem().toString();
//			vo.setProduct_sell(product_sell);
//
//			String product_content = content.getText();
//			vo.setProduct_content(product_content);
//
//			dao.getConnection();
//			dao.writeBoard(vo);
//			dao.disConnection();
//		}
//	}

	// 텍스트필드에 값이 입력되었는지를 판단하는 메서드
	private void checkText() {
		String txt = productName.getText();
		if(txt.isEmpty()) {
			btn.setEnabled(false);
		} else {
			btn.setEnabled(true);
		}	
	}
}