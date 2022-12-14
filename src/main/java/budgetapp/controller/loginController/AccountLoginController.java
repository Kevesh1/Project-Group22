package budgetapp.controller.loginController;

import budgetapp.controller.WindowController;
import dataaccess.mongodb.dao.account.AccountDao;
import budgetapp.model.account.Account;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Johannes
 * @author Jacob
 */
public class AccountLoginController extends BorderPane {

    @FXML
    private Button loginButton;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label wrongPassword;

    private final AccountDao accountDao;
    private Optional<Account> account;

    private WindowController windowController;

    public AccountLoginController(WindowController windowController) {
        this.windowController = windowController;
        accountDao = new AccountDao();
        loadCurrentView();
    }

    private void loadCurrentView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/loginView/AccountLoginView.fxml"));
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
    public void toRegistration() {
        windowController.showRegistrationScene();
    }

    @FXML
    public void onEnter(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)
        {
            tryLogin();
        }
        event.consume();
    }

    private void tryLogin() {
        account = accountDao.validateAccount(username.getText(), password.getText());
        if(account.isPresent()) {
            Stage stage = (Stage)this.getScene().getWindow();
            stage.close();
        }
        else loginFail();
    }

    @FXML
    public void loginEvent(Event event) {
        tryLogin();
        event.consume();
    }

    private void loginFail() {
        if(username.getText().isEmpty()){
            wrongPassword.setText("Please enter your name");
        }
        else if(password.getText().isEmpty()){
            wrongPassword.setText("Please enter your password");
        }
        else{
            wrongPassword.setText("Wrong username or password");
        }
    }

    public Optional<Account> getAccount() {
        return account;
    }

}