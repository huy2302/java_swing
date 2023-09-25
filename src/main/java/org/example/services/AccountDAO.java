package org.example.services;
import org.example.models.Account;
import org.example.views.UserLogin;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    private Connection conn;
    public AccountDAO() {
        conn = DatabaseManager.getConnection();
    }

    public boolean authenticate(String username, String password) {
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
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            String query = "SELECT * FROM taikhoan";
            rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                accounts.add(new Account(
                        id,
                        username,
                        password
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return accounts;
    }
    public boolean addAccount(String username, String password) {
        PreparedStatement preparedStatement = null;

        try {
            String query = "INSERT INTO `taikhoan` (`id`, `username`, `password`) VALUES (NULL, '"+username+"', '"+password+"');";
            preparedStatement = conn.prepareStatement(query);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean editAccount(int id, String username, String password) {
        PreparedStatement preparedStatement = null;

        try {
            String query = "UPDATE `taikhoan` SET `username` = '"+username+"', password = '"+password+"' WHERE `taikhoan`.`id` = " + id;
            preparedStatement = conn.prepareStatement(query);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean deleteAccount(int id) {
        PreparedStatement preparedStatement = null;

        try {
            String query = "DELETE FROM taikhoan WHERE id=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
