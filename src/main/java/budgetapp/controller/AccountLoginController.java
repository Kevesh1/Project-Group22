package budgetapp.controller;

import budgetapp.App;
import javafx.event.ActionEvent;
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
    private Button AccountLoginButton;

    @FXML
    private TextField AccountName;

    @FXML
    private PasswordField AccountPassword;

    @FXML
    private Label wrongPassword;

    @FXML
    public void accountLogin(ActionEvent event) throws IOException {
        accountValidity();
    }

    /*@FXML
    private void OpenIEWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FrontPage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/

    public void accountValidity() throws IOException {
        App app = new App();
        if(AccountName.getText().toString().equals("javatest") && AccountPassword.getText().toString().equals("Fun1")){
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
    }

}


