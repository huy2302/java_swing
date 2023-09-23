package org.example;

import org.example.views.UserLogin;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    UserLogin userLogin = new UserLogin();
//                    userLogin.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        SwingUtilities.invokeLater(() -> {
            // Khởi tạo và hiển thị giao diện đăng nhập
            UserLogin loginView = new UserLogin();
            loginView.setVisible(true);
        });
    }
}