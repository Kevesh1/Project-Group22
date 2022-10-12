package budgetapp.controller.login;

import DAO.MongoDB.AccountDao;
import budgetapp.model.account.Account;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

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
    private final RegistrationController registrationController;
    private Optional<Account> account;


    public AccountLoginController() {
        accountDao = new AccountDao();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/budgetapp/fxml/AccountLoginView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        } catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }
        registrationController = new RegistrationController();
    }

    public void showRegistration() {
        this.getScene().setRoot(registrationController);
    }

    @FXML
    public void toRegistration() {
        showRegistration();
    }

    @FXML
    public void loginEvent(Event event) {
        final Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        accountDao.getAllAccounts();
        account = accountDao.validateAccount(username.getText(), password.getText());
        if(account.isPresent()) {

            stage.close();
        }
        else loginFail();

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
        System.out.println("returning" + account.get().getUsername());
        return account;
    }

}


