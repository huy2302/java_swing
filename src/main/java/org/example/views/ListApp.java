package org.example.views;
import org.example.controllers.AccountController;
import org.example.models.Account;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListApp extends JFrame {
    private JTable studentTable;
    private JButton editButton;
    private JButton deleteButton;
    private AccountController accountController;

    public ListApp() {
        setTitle("Danh sách sinh viên");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        accountController = new AccountController();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        studentTable = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"ID", "Tên", "Tuổi"}, 0);
        studentTable.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        List<Account> accounts = accountController.getAllAccounts();
        for (Account account : accounts) {
            int id = account.getId();
            String username = account.getUsername();
            String password = account.getPassword();
            tableModel.addRow(new Object[]{id, username, password});
        }

        JPanel buttonPanel = new JPanel();
        editButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) studentTable.getValueAt(selectedRow, 0);
                    JOptionPane.showMessageDialog(ListApp.this, "row: " + selectedRow);

                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) studentTable.getValueAt(selectedRow, 0);
                    String username = (String) studentTable.getValueAt(selectedRow, 1);
                    String password = (String) studentTable.getValueAt(selectedRow, 2);

                    if (accountController.deleteAccount(id)) {
                        JOptionPane.showMessageDialog(ListApp.this, "Xóa thành công");
                        refreshTable();
                    } else {
                        JOptionPane.showMessageDialog(ListApp.this, "Xóa thất bại");
                    }
                }
            }
        });

        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        refreshTable();
    }

    private void refreshTable() {
        DefaultTableModel tableModel = (DefaultTableModel) studentTable.getModel();
        tableModel.setRowCount(0);

        List<Account> accounts = accountController.getAllAccounts();
        for (Account account : accounts) {
            tableModel.addRow(new Object[]{account.getId(), account.getUsername(), account.getPassword()});
        }
    }
}
