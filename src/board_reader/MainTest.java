package board_reader;

import javax.swing.*;
import java.util.Scanner;

public class MainTest {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MarketDAO marketDAO = new MarketDAO(); // DAO 객체 생성

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                MarketDAO marketDAO = new MarketDAO(); // DAO 객체 생성
                marketDAO.getMarket("2"); // getMarket 메서드 호출
            }
        });
    }
}
