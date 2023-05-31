package main;

import javax.swing.SwingUtilities;

import frame.LoginRegisterFrame;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginRegisterFrame loginRegisterFrame = new LoginRegisterFrame();
                loginRegisterFrame.setVisible(true);
            }
        });
    }
}