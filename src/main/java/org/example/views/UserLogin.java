package org.example.views;
import org.example.controllers.AccountController;
import org.example.services.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserLogin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private AccountController accountController;
    public UserLogin() {
        setTitle("Đăng nhập");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5); // Đặt khoảng cách giữa các thành phần

        JLabel usernameLabel = new JLabel("Tên đăng nhập:");
        JLabel passwordLabel = new JLabel("Mật khẩu:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        accountController = new AccountController();

        loginButton = new JButton("Đăng nhập");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                if (accountController.authenticate(username, password)) {
                    JOptionPane.showMessageDialog(UserLogin.this, "Đăng nhập thành công!");
                    dispose(); // Đóng màn hình đăng nhập
//                    new HomeApp().setVisible(true);
                    new ListApp().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(UserLogin.this, "Đăng nhập thất bại. Vui lòng thử lại.");
                }
            }
        });

        // Thêm các thành phần vào giao diện bằng GridBagLayout
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(usernameField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(passwordField, constraints);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // Sét giới hạn chiều rộng cho nút đăng nhập
        constraints.fill = GridBagConstraints.HORIZONTAL; // Đặt nút đăng nhập để căn theo chiều ngang
        panel.add(loginButton, constraints);

        add(panel);
    }
    private boolean authenticate(String username, String password) {
        Connection conn = DatabaseManager.getConnection();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            String query = "SELECT * FROM taikhoan WHERE username='" + username + "' AND password='" + password + "'";
            rs = st.executeQuery(query);

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(UserLogin.this, "Lỗi hệ thống, vui lòng thử lại sau!");
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(UserLogin.this, "Lỗi hệ thống, vui lòng thử lại sau!");

            }
        }
    }
}
