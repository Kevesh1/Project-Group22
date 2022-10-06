package budgetapp.controller;

import DAO.MongoDB.AccountDao;
import budgetapp.model.Account;
import budgetapp.model.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.List;

public class RegistrationController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label wrongPassword;

    private final AccountDao accountDao;

    public RegistrationController() {
        accountDao = new AccountDao();
    }

    @FXML
    public void registerAccountEvent(Event event) {

        if (!accountDao.accountExists(username.getText())) {

            Account account = registerAccount();
            wrongPassword.setText("Account: " + account.getUsername() + " created!");
        } else wrongPassword.setText("Account already exists!");
    }

    private Account registerAccount() {
        //TODO Create factory for account
        Account account = new Account(username.getText(), password.getText(), List.of(new User("TempUser", "templast", "temppass")));
        accountDao.updateAccount(account);
        return account;
    }
}
