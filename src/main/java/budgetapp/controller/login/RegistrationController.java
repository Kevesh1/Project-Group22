package budgetapp.controller.login;

import budgetapp.controller.WindowController;
import dataaccess.mongodb.dao.account.AccountDao;
import budgetapp.model.account.Account;
import dataaccess.mongodb.dao.account.UserDao;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class RegistrationController extends BorderPane {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label wrongPassword;

    private final WindowController windowController;

    private final AccountDao accountDao;
    private final UserDao userDao;

    public RegistrationController(WindowController windowController) {
        this.windowController = windowController;
        accountDao = new AccountDao();
        userDao = new UserDao();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/login/AccountRegistrationView.fxml"));
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
        Account account = new Account(username.getText(), password.getText());
        account = accountDao.addAccount(account);
        return account;
    }
    //TODO Change
    @FXML
    public void toLogin() {
        windowController.showLoginStage();
        /*AccountLoginController accountLoginController = new AccountLoginController();
        this.getScene().setRoot(accountLoginController);*/
    }

}
