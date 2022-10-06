package budgetapp.controller;

import DAO.MongoDB.AccountDao;
import budgetapp.App;
import budgetapp.model.Account;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AccountLoginController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label wrongPassword;

    private final AccountDao accountDao;

    public AccountLoginController() {
        accountDao = new AccountDao();
    }

    @FXML
    public void loginEvent(Event event) {
        accountDao.validateAccount(username.getText(), password.getText()).ifPresent(
                System.out::println
        );
        event.consume();
    }

    /*@FXML
    public void accountLogin(ActionEvent event) throws IOException {
        //accountValidity();
    }*/

    /*@FXML
    private void OpenIEWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FrontPage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/

    /*public void accountValidity() throws IOException {
        App app = new App();
        if(AccountName.getText().equals("javatest") && AccountPassword.getText().toString().equals("Fun1")){
            app.changeScene("FrontPage.fxml");
        }
        else if(AccountName.getText().isEmpty()){
            wrongPassword.setText("Please enter your name");
        }
        else if(AccountPassword.getText().isEmpty()){
            wrongPassword.setText("Please enter your password");
        }
        else{
            wrongPassword.setText("Wrong username or password");
        }
    }*/

}


