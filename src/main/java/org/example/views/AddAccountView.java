package org.example.views;

import org.example.controllers.AccountController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAccountView extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton addButton;
    private AccountController accountController;

    public AddAccountView(Frame parent) {
        super(parent, "Thêm tài khoản", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 2));

        accountController = new AccountController();

        JLabel usernameLabel = new JLabel("Tên đăng nhập:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordField = new JPasswordField();

        addButton = new JButton("Thêm");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // Thêm tài khoản
                if (accountController.addAccount(username, password)) {
                    JOptionPane.showMessageDialog(AddAccountView.this, "Thêm tài khoản thành công");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AddAccountView.this, "Thêm tài khoản thất bại");
                }
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(addButton);
    }
}
