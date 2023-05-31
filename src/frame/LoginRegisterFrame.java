package frame;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.LoginDAO;
import dao.MainDAO;
import db_info.DBProperties;
import vo.LoginVO;

public class LoginRegisterFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel loginPanel;
    private JPanel registerPanel;
    private JTabbedPane tabbedPane;
    
    private LoginVO loginVO;
    private static LoginVO loginUser;
    private LoginDAO loginDAO;

    public LoginRegisterFrame() {
        setTitle("로그인 및 회원가입");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocationRelativeTo(null);
        
        // 로그인 패널
        loginPanel = new JPanel();
        loginPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        loginPanel.setLayout(new GridBagLayout());
        
        // 이미지 삽입
        File file = new File(".");
		String rootPath = file.getAbsolutePath();
        ImageIcon imageIcon = new ImageIcon(rootPath + "/images/market.png");
        Image image = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(image);
       
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        
        
        JLabel usernameLabel = new JLabel("아이디:");
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        usernameField = new JTextField(20);
        loginPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        JLabel passwordLabel = new JLabel("비밀번호:");
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        passwordField = new JPasswordField(20);
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("로그인");
        loginPanel.add(loginButton, gbc);

        // 회원가입 패널
        registerPanel = new JPanel();
        registerPanel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel regUsernameLabel = new JLabel("아이디:");
        registerPanel.add(regUsernameLabel, gbc);

        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField regUsernameField = new JTextField(20);
        registerPanel.add(regUsernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        JLabel regPasswordLabel = new JLabel("비밀번호:");
        registerPanel.add(regPasswordLabel, gbc);

        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPasswordField regPasswordField = new JPasswordField(20);
        registerPanel.add(regPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        JLabel nameLabel = new JLabel("이름:");
        registerPanel.add(nameLabel, gbc);

        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField nameField = new JTextField(20);
        registerPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        JLabel addressLabel = new JLabel("주소:");
        registerPanel.add(addressLabel, gbc);

        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField addressField = new JTextField(20);
        registerPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        JLabel emailLabel = new JLabel("이메일:");
        registerPanel.add(emailLabel, gbc);

        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField emailField = new JTextField(20);
        registerPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        JLabel phoneNumberLabel = new JLabel("핸드폰 번호:");
        registerPanel.add(phoneNumberLabel, gbc);

        gbc.gridx++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField phoneNumberField = new JTextField(20);
        registerPanel.add(phoneNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        registerButton = new JButton("회원가입");
        registerPanel.add(registerButton, gbc);

        // 탭 패널
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("로그인", loginPanel);
        tabbedPane.addTab("회원가입", registerPanel);

     // 이미지 라벨 위치
        JLabel imageLabel = new JLabel(scaledImageIcon);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        add(imageLabel, BorderLayout.CENTER);
        add(tabbedPane, BorderLayout.SOUTH);
        
        loginDAO = new LoginDAO();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                loginVO = new LoginVO(username, password);
                if (loginDAO.check(loginVO)) {
                    JOptionPane.showMessageDialog(LoginRegisterFrame.this, "로그인 성공");
                    
                    //로그인 성공 -> 로그인계정 저장
                    setLoginUser(new LoginVO(username, password));
                    
                     //로그인 성공 시 BoardFrame 열기
                   MainDAO boardDAO = new MainDAO(DBProperties.URL, DBProperties.UID, DBProperties.UPW);

                   MainFrame boardFrame = new MainFrame(boardDAO); // 수정
                   boardFrame.setVisible(true);
                    dispose(); // 현재 창 닫기
                } else {
                    JOptionPane.showMessageDialog(LoginRegisterFrame.this, "로그인 실패, 다시 입력");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = regUsernameField.getText();
                String password = new String(regPasswordField.getPassword());
                String name = nameField.getText();
                String email = emailField.getText();
                String phonenumber = phoneNumberField.getText();
                String address = addressField.getText();

                if (loginDAO.checkUsernameExists(username)) {
                    JOptionPane.showMessageDialog(LoginRegisterFrame.this, "이미 존재하는 사용자명입니다.");
                } else {
                    loginVO = new LoginVO(username, password, name, email, phonenumber, address);
                    if (loginDAO.register(loginVO)) {
                        JOptionPane.showMessageDialog(LoginRegisterFrame.this, "회원가입 성공");
                        // 회원가입 성공 후 처리 로직 작성
                    } else {
                        JOptionPane.showMessageDialog(LoginRegisterFrame.this, "회원가입 실패");
                        // 회원가입 실패 후 처리 로직 작성
                    }
                }
            }
        });
    }

	public static LoginVO getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(LoginVO loginUser) {
		LoginRegisterFrame.loginUser = loginUser;
	}
}