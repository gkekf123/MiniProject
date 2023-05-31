package board_reader;

import javax.swing.*;

import dao.MainDAO;
import db_info.DBProperties;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import frame.LoginRegisterFrame;
import frame.MainFrame;

// 게시글 페이지, 댓글 페이지, 찜하기, 댓글 작성하는 곳?,
public class MarketDAO extends JFrame implements ActionListener {
    // 게시글 변수
    private String url = DBProperties.URL;
    private String uid = DBProperties.UID;
    private String upw = DBProperties.UPW;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel priceLabel;
    private JTextArea descriptionArea;
    private JButton likeButton;
    private JButton commentButton;
    private JLabel locationLabel;
    private JLabel sellLabel;
    private JLabel dateLabel;
    // -------------------------------------
    // 댓글 변수
    private JLabel productNameLabel;
    private JLabel commentLabel;
    private JTextField commentText;
    private JFrame getCommentsFrame;
    private JButton returnButton;
    private JFrame parentFrame; // 게시글 페이지 프레임
    private JPanel commentPanel; // 댓글이 표시될 패널
    private MainFrame mainFrame;


    public MarketDAO() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            System.out.println("CLASS FOR NAME ERR");
        }
    }
    public MarketDAO(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    // 게시글 페이지
    public void getMarket(String pull) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);

        try {
            conn = DriverManager.getConnection(url, uid, upw);
            String sql = "SELECT * FROM BOARDS B JOIN ACCOUNTS A ON B.ACCOUNT_ID = A.ACCOUNT_ID WHERE BOARD_NUM = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, pull);
            ResultSet resultSet = statement.executeQuery();

            // 좌표 설정 객체
            setLayout(null);

            // 제목 라벨
            titleLabel = new JLabel();
            titleLabel.setBounds(50, 30, 200, 15);
            add(titleLabel);

            // 아이디 라벨
            idLabel = new JLabel();
            idLabel.setBounds(50, 50, 200, 15);
            add(idLabel);

            // 가격 라벨
            priceLabel = new JLabel();
            priceLabel.setBounds(50, 70, 200, 15);
            add(priceLabel);

            // 날짜 라벨
            dateLabel = new JLabel();
            dateLabel.setBounds(50, 90, 200, 15);
            add(dateLabel);

            // 지역 라벨
            locationLabel = new JLabel();
            locationLabel.setBounds(50, 110, 200, 15);
            add(locationLabel);

            // 판매여부 라벨
            sellLabel = new JLabel();
            sellLabel.setBounds(50, 130, 200, 15);
            add(sellLabel);

            // 상품 설명 스크롤 패널
            JScrollPane descriptionScrollPane = new JScrollPane();
            descriptionScrollPane.setBounds(50, 160, 300, 280);

            descriptionArea = new JTextArea();
            descriptionArea.setEditable(false); // 내용 변경 가능 여부
            descriptionArea.setLineWrap(true); // 텍스트가 영역을 넘어갈 경우 자동 줄바꿈
            descriptionScrollPane.setViewportView(descriptionArea);
            add(descriptionScrollPane);

            // 찜하기 버튼
            likeButton = new JButton("찜하기");
            Dimension buttonSize = new Dimension(80, 30);
            likeButton.setPreferredSize(buttonSize);
            likeButton.setBounds(50, 480, 100, 30);
            add(likeButton);

            // 댓글 버튼
            commentButton = new JButton("댓글쓰기");
            commentButton.setPreferredSize(buttonSize);
            commentButton.setBounds(250, 480, 100, 30);
            add(commentButton);

            JButton backButton = new JButton("뒤로가기");
            backButton.setBounds(280, 20, 100, 30);
            add(backButton);

            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // MainFrame 클래스의 인스턴스 생성
                    MainDAO mainDAO = new MainDAO(DBProperties.URL, DBProperties.UID, DBProperties.UPW);
                   frame.MainFrame mainFrame = new frame.MainFrame(mainDAO);
                    mainFrame.setVisible(true);

                    // 현재 프레임 종료
                    dispose();
                }
            });


            // 게시글 데이터 표시
            if (resultSet.next()) {
                titleLabel.setText("제목: " + resultSet.getString("product_name"));
                priceLabel.setText("가격: " + resultSet.getString("price") + "원");
                idLabel.setText("작성자: " + resultSet.getString("account_id"));
                dateLabel.setText("등록날짜: " + resultSet.getString("board_date"));
                sellLabel.setText("판매여부: " + resultSet.getString("product_sell"));
                locationLabel.setText("거래 지역 : " + resultSet.getString("address"));
                descriptionArea.setText(resultSet.getString("product_content"));
            } else {
                JOptionPane.showMessageDialog(this, "게시글을 찾을 수 없습니다.");
            }
            setLocationRelativeTo(null);


            // 찜하기 버튼 클릭
            likeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Connection conn = null;
                    PreparedStatement pstmt = null;

                    try {
                        conn = DriverManager.getConnection(url, uid, upw);
                        String checkSql = "SELECT * FROM LIKES WHERE ACCOUNT_ID = ? AND BOARD_NUM = ?";
                        PreparedStatement checkStatement = conn.prepareStatement(checkSql);
                        checkStatement.setString(1, LoginRegisterFrame.getLoginUser().getACCOUNT_ID()); // 전달 받은 ID로 입력
                        checkStatement.setString(2, pull);
                        ResultSet checkResult = checkStatement.executeQuery();

                        if (checkResult.next()) {
                            JOptionPane.showMessageDialog(null, "이미 추가되어 있습니다.");
                        } else {
                            String insertSql = "INSERT INTO LIKES VALUES (?, ?)";
                            PreparedStatement insertStatement = conn.prepareStatement(insertSql);
                            insertStatement.setString(1, LoginRegisterFrame.getLoginUser().getACCOUNT_ID());
                            insertStatement.setString(2, pull);
                            int rowsInserted = insertStatement.executeUpdate();

                            if (rowsInserted > 0) {
                                JOptionPane.showMessageDialog(null, "찜 목록에 추가되었습니다.");
                            } else {
                                JOptionPane.showMessageDialog(null, "찜 목록에 추가하는데 실패하였습니다.");
                            }

                            insertStatement.close();
                        }

                        // 데이터베이스 연결 종료
                        checkResult.close();
                        checkStatement.close();
                        conn.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            // 데이터베이스 연결 종료
            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);

        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // getComments 페이지로 넘어가는 동작을 수행
                getComments(pull);
            }
        });
    }

    // 댓글 페이지(보기)
    public void getComments(String num) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        getCommentsFrame = new JFrame("댓글 페이지");
        getCommentsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getCommentsFrame.setSize(400, 600);

        // 멤버 변수 초기화
        productNameLabel = new JLabel();
        priceLabel = new JLabel();
        commentText = new JTextField();
        commentButton = new JButton("작성");
        returnButton = new JButton("뒤로가기");

        // 판매 정보
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.add(productNameLabel, BorderLayout.NORTH);
        infoPanel.add(priceLabel, BorderLayout.CENTER);
        getCommentsFrame.add(infoPanel, BorderLayout.NORTH);

        // 댓글을 표기할 패널 초기화
        commentPanel = new JPanel();
        commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));

        try {
            conn = DriverManager.getConnection(url, uid, upw);
            String sql = "SELECT COMMENT_NUM, C.ACCOUNT_ID, COMMENT_CONTENT, COMMENT_DATE, B.PRODUCT_NAME, B.PRICE, B.PRODUCT_SELL FROM COMMENTS C JOIN BOARDS B ON C.BOARD_NUM = B.BOARD_NUM WHERE B.BOARD_NUM = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, num);
            ResultSet resultSet = statement.executeQuery();

            // 전체 패널
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

            // 스크롤 패널
            JScrollPane scrollPane = new JScrollPane(mainPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setBounds(50, 160, 300, 280);
            getCommentsFrame.add(scrollPane, BorderLayout.CENTER);

            // 댓글 데이터 표시
            while (resultSet.next()) {
                // 댓글 패널
                JPanel commentPanel = new JPanel();
                commentPanel.setBorder(BorderFactory.createEtchedBorder());
                commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
                commentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

                // 댓글 내용 라벨
                JLabel commentLabel = new JLabel();
                commentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                commentLabel.setText("내용 : " + resultSet.getString("comment_content"));
                commentPanel.add(commentLabel);

                // 작성자 라벨
                JLabel idLabel = new JLabel();
                idLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                idLabel.setText("작성자 : " + resultSet.getString("account_id"));
                commentPanel.add(idLabel);

                // 작성날짜 라벨
                JLabel dateLabel = new JLabel();
                dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                dateLabel.setText("작성날짜 : " + resultSet.getDate("comment_date"));
                commentPanel.add(dateLabel);

                // 댓글 패널을 전체 패널에 추가
                mainPanel.add(commentPanel);
                mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 댓글 간 간격 조정
            }

            // 데이터베이스 연결 종료
            resultSet.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(getCommentsFrame, "오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }

        // 댓글 입력 영역
        JPanel commentInputPanel = new JPanel();
        commentInputPanel.setLayout(new BorderLayout());
        commentButton.addActionListener(this);
        commentInputPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        commentInputPanel.add(commentText, BorderLayout.CENTER);
        commentInputPanel.add(commentButton, BorderLayout.EAST);
        getCommentsFrame.add(commentInputPanel, BorderLayout.SOUTH);

        // 뒤로가기 버튼
        JPanel returnButtonPanel = new JPanel();
        returnButtonPanel.setPreferredSize(new Dimension(100, 50));
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getCommentsFrame.dispose(); // getCommentsFrame 닫기
                setVisible(true); // getMarket 페이지 다시 보이게 설정
            }
        });
        returnButtonPanel.add(returnButton);
        getCommentsFrame.add(returnButtonPanel, BorderLayout.EAST);

        // 상품 정보 설정
        try {
            conn = DriverManager.getConnection(url, uid, upw);
            String productSql = "SELECT PRODUCT_NAME, PRICE FROM BOARDS WHERE BOARD_NUM = ?";
            PreparedStatement productStatement = conn.prepareStatement(productSql);
            productStatement.setString(1, num);
            ResultSet productResultSet = productStatement.executeQuery();

            if (productResultSet.next()) {
                productNameLabel.setText("상품명: " + productResultSet.getString("PRODUCT_NAME"));
                priceLabel.setText("가격: " + productResultSet.getString("PRICE") + "원");
            }

            productResultSet.close();
            productStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(getCommentsFrame, "오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
        getCommentsFrame.setLocationRelativeTo(null);
        getCommentsFrame.setVisible(true);
        setVisible(false);
    }

    // 댓글 페이지 (쓰기)
    public void actionPerformed(ActionEvent e) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, uid, upw);
            String comment = commentText.getText();
            String sql = "INSERT INTO COMMENTS VALUES (COMMENTS_SEQ.NEXTVAL, ?, ?, ?, sysdate)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, MainFrame.getBoardUser().getBOARD_NUM()); // 상품번호 Pull
            statement.setString(2, LoginRegisterFrame.getLoginUser().getACCOUNT_ID());
            statement.setString(3, comment); // 댓글
            statement.executeUpdate();
            statement.close();

            // 댓글을 추가한 후 화면에 표시합니다.
//            JLabel newCommentLabel = new JLabel();
//            newCommentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//            newCommentLabel.setText("내용 : " + comment);
//            commentPanel.add(newCommentLabel);
//            commentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // 댓글 간 간격 조정

            JOptionPane.showMessageDialog(getCommentsFrame, "댓글이 입력되었습니다.");
            getCommentsFrame.dispose(); // 댓글 페이지 닫기
            getComments(MainFrame.getBoardUser().getBOARD_NUM());


            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // setVisible(flase);
        // 댓글 입력 필드를 초기화합니다.
        commentText.setText("");
        // 화면을 갱신
        commentPanel.revalidate();
        commentPanel.repaint();

        // 댓글 입력 필드를 종료합니다.
        // getCommentsFrame.dispose();
    }
}
