package org.example.controllers;
import org.example.models.Account;
import org.example.services.AccountDAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AccountController {
    private AccountDAO accountDAO;


    public AccountController() {
        accountDAO = new AccountDAO();
    }

    public boolean authenticate(String username, String password) {
        return accountDAO.authenticate(username, password);
    }

    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    public boolean addAccount(String username, String password) {
        return accountDAO.addAccount(username, password);
    }
    public boolean deleteAccount(int id) {
        return accountDAO.deleteAccount(id);
    }
}
