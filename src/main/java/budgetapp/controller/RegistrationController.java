package budgetapp.controller;

import DAO.MongoDB.AccountDao;
import budgetapp.model.Account;
import budgetapp.model.User;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RegistrationController extends BorderPane {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label wrongPassword;

    private final AccountDao accountDao;

    public RegistrationController() {
        accountDao = new AccountDao();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/AccountRegistrationView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        } catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
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
        Account account = new Account(username.getText(), password.getText(), List.of(new User(username.getText(), "Account", null)));
        accountDao.updateAccount(account);
        return account;
    }
    //TODO Change
    @FXML
    public void toLogin() {
        Scene scene = this.getScene();
        scene.setRoot(new AccountLoginController());
    }

}
